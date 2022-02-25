package com.jj.mall.admin.service;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.model.UmsAdmin;
import com.jj.mall.model.UmsRole;

import java.util.List;

/**
 *  后台管理员service
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
public interface UserAdminService {

    /**
     * 获取角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     *  获取用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    CommonResult login(String username, String password);
}
