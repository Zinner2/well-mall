package com.jj.mall.dao;

import com.jj.mall.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品满减Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface PmsProductFullReductionDao {
    /**
     * 批量创建
     * @param productFullReductionList 商品满减List
     * @return
     */
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);

}
