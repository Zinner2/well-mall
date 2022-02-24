package com.jj.mall.auth.service;

import com.jj.mall.common.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@FeignClient("mall-admin")
public interface UserAdminService {
    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    @GetMapping("/admin/loadByUserName")
    UserDto loadByUserName(String userName);
}
