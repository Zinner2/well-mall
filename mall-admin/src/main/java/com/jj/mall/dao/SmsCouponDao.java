package com.jj.mall.dao;

import com.jj.mall.dto.SmsCouponParam;

/**
 * 优惠券管理 Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsCouponDao {
    /**
     * 获取单个优惠券详细信息
     * @param id
     * @return
     */
    SmsCouponParam getItem(Long id);
}
