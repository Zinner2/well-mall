package com.jj.mall.common.exception;

import com.jj.mall.common.api.IErrorCode;

/**
 * 断言处理类, 用与处理各种Api处理类
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */
public class Asserts {
    public static void fail(IErrorCode errorCode){
        throw new ApiException(errorCode);
    }

    public static void fail(String message){
        throw new ApiException(message);
    }
}
