package com.jj.mall.dao;

import com.jj.mall.model.SmsCouponProductCategoryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优惠卷和产品分类Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsCouponProductCategoryRelationDao {
    /**
     * 批量创建
     * @param productCategoryRelationList
     * @return
     */
    int insertList(@Param("list") List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}
