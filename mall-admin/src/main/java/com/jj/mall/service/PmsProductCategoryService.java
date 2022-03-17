package com.jj.mall.service;

import com.jj.mall.dto.PmsProductCategoryParam;
import com.jj.mall.dto.PmsProductCategoryWithChildrenItem;
import com.jj.mall.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 根据id获取商品分类信息
     * @param id
     * @return
     */
    PmsProductCategory getItem(Long id);

    /**
     * 添加产品分类
     * @param productCategoryParam
     * @return
     */
    @Transactional
    int create(PmsProductCategoryParam productCategoryParam);

    /**
     *
     * 修改产品分类
     * @param id
     * @param productCategoryParam
     * @return
     */
    @Transactional
    int update(Long id, PmsProductCategoryParam productCategoryParam);

    /**
     * 删除产品分类
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 批量修改导航状态
     * @param ids
     * @param navStatus
     * @return
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);
}
