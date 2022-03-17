package com.jj.mall.dao;

import com.jj.mall.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
public interface PmsProductCategoryAttributeRelationDao {

    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);

}
