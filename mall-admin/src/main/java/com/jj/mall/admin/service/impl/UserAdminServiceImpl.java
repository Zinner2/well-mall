package com.jj.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.core.util.Assert;
import com.jj.mall.admin.dao.UmsAdminRoleRelationDao;
import com.jj.mall.admin.service.AuthService;
import com.jj.mall.admin.service.UmsAdminCacheService;
import com.jj.mall.admin.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.api.ResultCode;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.common.exception.Asserts;
import com.jj.mall.common.utils.DataUtils;
import com.jj.mall.mapper.UmsAdminLoginLogMapper;
import com.jj.mall.mapper.UmsAdminMapper;
import com.jj.mall.model.UmsAdmin;
import com.jj.mall.model.UmsAdminExample;
import com.jj.mall.model.UmsAdminLoginLog;
import com.jj.mall.model.UmsRole;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;
    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Resource
    private AuthService authService;
    @Resource
    private UmsAdminLoginLogMapper adminLoginLogMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private UmsAdminCacheService adminCacheService;
    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getUmsAdminRoleList(adminId);
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(adminExample);
        if(umsAdminList != null && umsAdminList.size() > 0){
            return umsAdminList.get(0);
        }
        return null;
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin != null){
            List<UmsRole> roleList = getRoleList(admin.getId());
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(admin, userDto);
            if (CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> item.getId() +"-"+item.getName())
                        .collect(Collectors.toList());
                userDto.setRoles(roleStrList);
            }
            return userDto;
        }
        return null;
    }

    @Override
    public CommonResult login(String username, String password) {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            Asserts.fail("用户名或者密码不能为空");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("grant_type","password");
        params.put("client_secret","123456");
        params.put("username",username);
        params.put("password",password);
        CommonResult result = authService.getAccessToken(params);
        if(result.getCode() == ResultCode.SUCCESS.getCode() && result.getData() != null){
            // 插入登录日志
            insertLoginLog(username);
        }
        return result;
    }

    @Override
    public UmsAdmin getCurrentAdmin() {
        // 请求头获取token进行解析
        String header = request.getHeader(AuthConstant.JWT_TOKEN_PREFIX);
        UserDto userDto = JSONUtil.toBean(header, UserDto.class);
        if (userDto == null) {
           Asserts.fail("登录头 header 为空");
        }
        UmsAdmin admin = adminCacheService.getAdmin(userDto.getId());
        if(admin != null){
           return admin;
        }else {
           admin = umsAdminMapper.selectByPrimaryKey(userDto.getId());
           adminCacheService.setAdmin(admin);
           return admin;
        }
    }

    public void insertLoginLog(String username){
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        UmsAdmin admin = getAdminByUsername(username);
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(DataUtils.getLocalCurrentTime());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        adminLoginLogMapper.insert(loginLog);
    }
    public UmsAdmin getAdmin(Long adminId){
        if(StringUtils.isEmpty(adminId)){
            Asserts.fail("用户Id为空");
        }
        return  umsAdminMapper.selectByPrimaryKey(adminId);
    }
}
