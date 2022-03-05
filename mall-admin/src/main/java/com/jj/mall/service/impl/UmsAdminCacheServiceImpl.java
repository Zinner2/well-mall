package com.jj.mall.service.impl;

import com.jj.mall.service.UmsAdminCacheService;
import com.jj.mall.common.service.RedisService;
import com.jj.mall.model.UmsAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台登录用户缓存ServiceImpl
 * @author 任人子
 * @date 2022/2/28  - {TIME}
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Resource
    private RedisService redisService;
    @Value("${redis.database}")
    private String redisDatabase;
    @Value("${redis.key.admin}")
    private String redisKeyAdmin;
    @Value("${redis.expire.common}")
    private Long redisExpireCommon;

    @Override
    public UmsAdmin getAdmin(Long adminId) {
        String key = redisDatabase + ":" + redisKeyAdmin +':' +adminId;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = redisDatabase + ":" + redisKeyAdmin +':' +admin.getId();
        redisService.set(key, admin, redisExpireCommon);
    }
}
