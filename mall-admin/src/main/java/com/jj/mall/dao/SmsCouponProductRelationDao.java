package com.jj.mall.dao;

import com.jj.mall.model.SmsCouponProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义优惠券和商品关系关系Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsCouponProductRelationDao {
    /**
     * 批量创建
     * @param productRelationList
     */
    void insertList(@Param("list") List<SmsCouponProductRelation> productRelationList);
}
