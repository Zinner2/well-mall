package com.jj.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.xiaoymin.knife4j.core.util.Assert;
import com.jj.mall.admin.dao.UmsAdminRoleRelationDao;
import com.jj.mall.admin.service.AuthService;
import com.jj.mall.admin.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.api.ResultCode;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.common.exception.Asserts;
import com.jj.mall.mapper.UmsAdminMapper;
import com.jj.mall.model.UmsAdmin;
import com.jj.mall.model.UmsAdminExample;
import com.jj.mall.model.UmsRole;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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

        }
        return result;
    }


}
