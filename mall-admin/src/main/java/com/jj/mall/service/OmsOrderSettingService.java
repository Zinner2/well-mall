package com.jj.mall.service;

import com.jj.mall.model.OmsOrderSetting;

/**
 * 订单设置 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsOrderSettingService {
    /**
     * 获取指定订单设置
     * @param id
     * @return
     */
    OmsOrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     * @param id
     * @param orderSetting
     * @return
     */
    int update(Long id, OmsOrderSetting orderSetting);
}
