package com.jj.mall.dao;

import com.jj.mall.model.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义会员价格Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface PmsMemberPriceDao {

    /**
     * 批量创建
     * @param memberPriceList 会员价格List
     * @return
     */
    int insertList(@Param("list")List<PmsMemberPrice> memberPriceList);
}
