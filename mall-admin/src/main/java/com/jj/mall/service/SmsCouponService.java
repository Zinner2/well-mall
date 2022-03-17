package com.jj.mall.service;

import com.jj.mall.dto.SmsCouponParam;
import com.jj.mall.model.SmsCoupon;

import java.util.List;

/**
 * 优惠卷管理 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsCouponService {
    /**
     * 分页查询优惠券列表
     * @param name
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取单个优惠券的详细信息
     * @param id
     * @return
     */
    SmsCouponParam getItem(Long id);

    /**
     * 添加优惠券
     * @param couponParam
     * @return
     */
    int create(SmsCouponParam couponParam);

    /**
     * 删除优惠券
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改优惠券
     * @param id
     * @param couponParam
     * @return
     */
    int update(Long id, SmsCouponParam couponParam);
}
