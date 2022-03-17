package com.jj.mall.dao;

import com.jj.mall.dto.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品参数Dao
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
public interface PmsProductAttributeDao {
    /**
     * 根据商品分类的id获取商品属性及属性分类
     * @param productCategoryId
     * @return
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("id")Long productCategoryId);
}
