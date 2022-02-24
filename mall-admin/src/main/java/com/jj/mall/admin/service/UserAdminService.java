package com.jj.mall.admin.service;

import com.jj.mall.common.domain.UserDto;

/**
 *  后台管理员service
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
public interface UserAdminService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);
}
