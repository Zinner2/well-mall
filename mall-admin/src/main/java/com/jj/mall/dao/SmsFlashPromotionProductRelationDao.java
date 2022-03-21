package com.jj.mall.dao;

import com.jj.mall.dto.SmsFlashPromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 限时购商品关系Dao
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
public interface SmsFlashPromotionProductRelationDao {
    /**
     * 获取限时购及相关商品信息
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
