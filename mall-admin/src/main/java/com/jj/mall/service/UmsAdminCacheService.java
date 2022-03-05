package com.jj.mall.service;

import com.jj.mall.model.UmsAdmin;

/**
 * 后台登录用户缓存Service
 * @author 任人子
 * @date 2022/2/28  - {TIME}
 */
public interface UmsAdminCacheService {

    /**
     * 从redis获取用户信息
     * @param adminId
     * @return
     */
    UmsAdmin getAdmin(Long adminId);
    /**
     * 用户信息保存到redis中
     * @param admin
     * @return
     */
    void setAdmin(UmsAdmin admin);
}
