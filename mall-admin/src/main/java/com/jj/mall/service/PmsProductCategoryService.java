package com.jj.mall.service;

import com.jj.mall.dto.PmsProductCategoryWithChildrenItem;
import com.jj.mall.model.PmsProductCategory;

import java.util.List;

/**
 * 后台商品目录Service
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsProductCategoryService {
    /**
     * 分页获取商品目录列表
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductCategory> list(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 查询所有目录及子级目录
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> withChildren();
}
