package com.jj.mall.common.exception;

import com.jj.mall.common.api.IErrorCode;

/**
 * 自定义Api异常处理
 *
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */
public class ApiException extends RuntimeException {
    private IErrorCode iErrorCode;

    public ApiException(IErrorCode iErrorCode) {
        super(iErrorCode.getMessage());
        this.iErrorCode = iErrorCode;
    }
    public ApiException(String message){
        super(message);
    }
}
