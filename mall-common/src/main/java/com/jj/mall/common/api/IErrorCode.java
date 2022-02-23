package com.jj.mall.common.api;

/**
 * 封装Api错误状态码
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
public interface IErrorCode {
    /**
     * 获取状态码
     * @return
     */
    long getCode();

    /**
     * 获取信息
     * @return
     */
    String getMessage();

}
