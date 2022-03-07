package com.jj.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.UmsAdminRoleRelationDao;
import com.jj.mall.dto.UmsAdminParam;
import com.jj.mall.mapper.UmsAdminRoleRelationMapper;
import com.jj.mall.model.*;
import com.jj.mall.service.AuthService;
import com.jj.mall.service.UmsAdminCacheService;
import com.jj.mall.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.api.ResultCode;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.common.exception.Asserts;
import com.jj.mall.common.utils.DataUtils;
import com.jj.mall.mapper.UmsAdminLoginLogMapper;
import com.jj.mall.mapper.UmsAdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);
    @Resource
    private UmsAdminMapper adminMapper;
    @Resource
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Resource
    private UmsAdminLoginLogMapper loginLogMapper;
    @Resource
    private AuthService authService;
    @Resource
    private UmsAdminCacheService adminCacheService;
    @Resource
    private HttpServletRequest request;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }


    @Override
    public CommonResult login(String username, String password) {
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        CommonResult restResult = authService.getAccessToken(params);
        if (ResultCode.SUCCESS.getCode() == restResult.getCode() && restResult.getData() != null) {
            insertLoginLog(username);
        }
        return restResult;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin == null) {
            return;
        }
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin record = new UmsAdmin();
        record.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(record, example);
    }


    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }


    @Override
    public UserDto loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsRole> roleList = getRoleList(admin.getId());
            UserDto userDTO = new UserDto();
            BeanUtils.copyProperties(admin, userDTO);
            if (CollUtil.isNotEmpty(roleList)) {
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }

    @Override
    public UmsAdmin getCurrentAdmin() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StrUtil.isEmpty(userStr)) {
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        UmsAdmin admin = adminCacheService.getAdmin(userDto.getId());
        if (admin != null) {
            return admin;
        } else {
            admin = adminMapper.selectByPrimaryKey(userDto.getId());
            adminCacheService.setAdmin(admin);
            return admin;
        }
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample adminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = adminExample.createCriteria();
        if (!StringUtils.isEmpty(keyword)){
            criteria.andUsernameLike("%" + keyword + "%");
            adminExample.or().andNickNameLike("%" + keyword + "%");
        }
        return adminMapper.selectByExample(adminExample);
    }

    @Override
    public UmsAdmin register(UmsAdminParam adminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(1);
        umsAdmin.setCreateTime(new Date());
        BeanUtils.copyProperties(adminParam, umsAdmin);
        UmsAdminExample adminExample = new UmsAdminExample();
        // 查询是否有重名的用户
        adminExample.createCriteria().andUsernameEqualTo(adminParam.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(adminExample);
        if(umsAdminList.size() > 0) {
            return null;
        }
        umsAdmin.setPassword(BCrypt.hashpw(umsAdmin.getPassword()));
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public int update(UmsAdmin umsAdmin) {
        UmsAdmin selectAdmin = adminMapper.selectByPrimaryKey(umsAdmin.getId());
        if(umsAdmin.getPassword().equals(selectAdmin.getPassword())){
            //与原加密密码相同的不需要修改
            umsAdmin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(umsAdmin.getPassword())){
                umsAdmin.setPassword(null);
            }else{
                umsAdmin.setPassword(BCrypt.hashpw(umsAdmin.getPassword()));
            }
        }
        int result = adminMapper.updateByPrimaryKeySelective(umsAdmin);
        // 将缓存中的用户信息删除
        adminCacheService.delAdmin(umsAdmin.getId());
        return result;
    }

    @Override
    public int delete(Long id) {
        int res = adminMapper.deleteByPrimaryKey(id);
        adminCacheService.delAdmin(id);
        return res;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();

        UmsAdminRoleRelationExample roleRelationExample = new UmsAdminRoleRelationExample();
        roleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        // 先删除原来关系
        adminRoleRelationMapper.deleteByExample(roleRelationExample);
        // 建立新的关系
        if(!CollectionUtils.isEmpty(roleIds)){
            List<UmsAdminRoleRelation> relationList = new ArrayList<>();
            for(Long roleId : roleIds){
                UmsAdminRoleRelation role = new UmsAdminRoleRelation();
                role.setRoleId(roleId);
                role.setAdminId(adminId);
                relationList.add(role);
            }
            adminRoleRelationDao.insertList(relationList);
        }
        return count;
    }

    @Override
    public int updateStatus(UmsAdmin umsAdmin) {
        int count = adminMapper.updateByPrimaryKeySelective(umsAdmin);
        adminCacheService.delAdmin(umsAdmin.getId());
        return count;
    }
}
