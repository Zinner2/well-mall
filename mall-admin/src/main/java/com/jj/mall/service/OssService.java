package com.jj.mall.service;

import com.jj.mall.dto.OssCallbackResult;
import com.jj.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss上传Service
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
