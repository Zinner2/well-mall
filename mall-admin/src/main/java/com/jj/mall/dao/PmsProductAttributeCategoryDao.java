package com.jj.mall.dao;

import com.jj.mall.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 商品属性分类Dao
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
public interface PmsProductAttributeCategoryDao {
    /**
     *获取包含属性的商品属性分类
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
