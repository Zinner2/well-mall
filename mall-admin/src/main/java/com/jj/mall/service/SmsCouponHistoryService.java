package com.jj.mall.service;

import com.jj.mall.model.SmsCouponHistory;

import java.util.List;

/**
 * 优惠券领取记录 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsCouponHistoryService {
    /**
     * 分页获取领取记录列表
     * @param couponId
     * @param useStatus
     * @param orderSn
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
