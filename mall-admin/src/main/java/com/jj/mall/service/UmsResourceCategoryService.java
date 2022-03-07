package com.jj.mall.service;

import com.jj.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * 后台资源目录管理Service
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源目录
     * @return
     */
    List<UmsResourceCategory> listAllCategories();

    /**
     * 创建资源目录
     * @param resourceCategory
     * @return
     */
    int createCategory(UmsResourceCategory resourceCategory);

    /**
     * 修改资源目录
     * @param id
     * @param resourceCategory
     * @return
     */
    int updateCategory(Long id, UmsResourceCategory resourceCategory);

    /**
     * 删除资源目录
     * @param id
     * @return
     */
    int deleteCategory(Long id);
}
