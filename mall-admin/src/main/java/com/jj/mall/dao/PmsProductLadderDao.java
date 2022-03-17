package com.jj.mall.dao;

import com.jj.mall.model.PmsMemberPrice;
import com.jj.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义会员阶梯价格Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface PmsProductLadderDao {
    /**
     * 批量创建
     * @param productLadderList 阶梯价格List
     * @return
     */
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
