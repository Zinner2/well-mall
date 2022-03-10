package com.jj.mall.service;

import com.jj.mall.model.PmsProductAttribute;

import java.util.List;

/**
 * 后台商品参数Service
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsProductAttributeService {

    /**
     * 分页获取商品参数列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductAttribute> category(Integer pageNum, Integer pageSize);
}
