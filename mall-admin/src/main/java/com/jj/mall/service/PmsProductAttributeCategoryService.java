package com.jj.mall.service;

import com.jj.mall.dto.PmsProductAttributeCategoryItem;
import com.jj.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类Service
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
public interface PmsProductAttributeCategoryService {
    /**
     *获取所有商品属性分类以及其下属性
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();

    /**
     * 分页查询属性分类
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 添加属性分类
     * @param name
     * @return
     */
    int create(String name);

    /**
     * 修改属性分类
     * @param id
     * @param name
     * @return
     */
    int update(Long id, String name);

    /**
     * 删除属性分类
     * @param id
     * @return
     */
    int delete(Long id);
}
