package com.jj.mall.common.api;
import lombok.Data;
/**
 * 通用返回对象
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功放回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<T>(errorCode.getCode(),errorCode.getMessage(),null);
    }
    /**
     * 失败返回结果
     * @param errorMessage
     * @return
     * @param <T>
     */
    public static <T> CommonResult<T> failed(String errorMessage){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),errorMessage,null);
    }

    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(),message,null);
    }

    public static  <T> CommonResult<T>  unauthorized(String message) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),message,null);
    }
}
