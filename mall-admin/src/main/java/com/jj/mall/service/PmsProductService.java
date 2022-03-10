package com.jj.mall.service;

import com.jj.mall.model.PmsProduct;

import java.util.List;

/**
 * 后台商品管理Service
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsProductService {

    /**
     * 分页查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProduct> list(Integer pageNum, Integer pageSize);
}
