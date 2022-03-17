package com.jj.mall.service.impl;

import com.jj.mall.mapper.OmsOrderSettingMapper;
import com.jj.mall.model.OmsOrderSetting;
import com.jj.mall.service.OmsOrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单设置ServiceImpl
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {
    @Resource
    private OmsOrderSettingMapper orderSettingMapper;

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateByPrimaryKeySelective(orderSetting);
    }

    @Override
    public OmsOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }
}
