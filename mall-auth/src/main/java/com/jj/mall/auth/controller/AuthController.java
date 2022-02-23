package com.jj.mall.auth.controller;

import com.jj.mall.auth.domain.Oauth2TokenDto;
import com.jj.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义Oauth2获取令牌接口
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
@RestController
@Api(tags = "authController",value = "认证中心登入认证")
@RequestMapping("/oauth")
public class AuthController {
    @PostMapping("/token")
    public CommonResult<Oauth2TokenDto> postAccessToken(){

    }
}
