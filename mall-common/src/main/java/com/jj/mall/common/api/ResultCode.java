package com.jj.mall.common.api;

/**
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
public enum  ResultCode implements IErrorCode {
    SUCCESS(200,"操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404,"参数校验失败"),
    UNAUTHORIZED(401,"暂未登录或token已过期"),
    FORBIDDEN(403,"暂无相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
