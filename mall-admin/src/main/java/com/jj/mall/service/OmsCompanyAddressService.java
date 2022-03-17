package com.jj.mall.service;

import com.jj.mall.model.OmsCompanyAddress;

import java.util.List;

/**
 *  收货地址管理 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsCompanyAddressService {
    /**
     * 获取所有收获地址
     * @return
     */
    List<OmsCompanyAddress> list();
}
