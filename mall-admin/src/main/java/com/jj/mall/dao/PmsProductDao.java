package com.jj.mall.dao;

import com.jj.mall.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义商品管理Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     * @param id
     * @return
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
