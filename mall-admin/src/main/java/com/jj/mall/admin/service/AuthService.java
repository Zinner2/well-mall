package com.jj.mall.admin.service;

import com.jj.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * 远程调用微服务
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */
@FeignClient("mall-auth")
public interface AuthService {
    @PostMapping("/oauth/token")
    CommonResult getAccessToken(Map<String,String> params);
}
