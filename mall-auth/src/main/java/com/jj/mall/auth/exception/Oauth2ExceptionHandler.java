package com.jj.mall.auth.exception;

import com.jj.mall.common.api.CommonResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e){
        return CommonResult.failed(e.getMessage());
    }
}
