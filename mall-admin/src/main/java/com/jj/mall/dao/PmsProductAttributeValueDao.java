package com.jj.mall.dao;

import com.jj.mall.model.PmsProductAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品参数，商品自定义规格属性Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface PmsProductAttributeValueDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}
