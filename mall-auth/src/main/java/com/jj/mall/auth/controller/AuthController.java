package com.jj.mall.auth.controller;

import com.jj.mall.auth.domain.Oauth2TokenDto;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.constant.AuthConstant;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
@RestController
@Api(tags = "authController",value = "认证中心登入认证")
@RequestMapping("/oauth")
public class AuthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public CommonResult<Oauth2TokenDto> postAccessToken(@ApiIgnore Principal principal,@ApiIgnore @RequestParam Map<String, String> params) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, params).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                                        .token(oAuth2AccessToken.getValue())
                                        .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                                        .tokenHeader(AuthConstant.JWT_TOKEN_PREFIX)
                                        .build();
        return CommonResult.success(oauth2TokenDto);
    }
}
