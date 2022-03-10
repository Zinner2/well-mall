package com.jj.mall.dao;

import com.jj.mall.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 后台商品目录Dao
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsProductCategoryDao {

    /**
     * 查询所有目录及子级目录
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> withChildren();
}
