package com.jj.mall.service;

import com.jj.mall.dto.SmsFlashPromotionProduct;
import com.jj.mall.model.SmsFlashPromotionProductRelation;

import java.util.List;

/**
 * 限时购和商品关系管理Service
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 根据活动和场次Id 获取商品关系数量
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    Long getCount(Long flashPromotionId, Long flashPromotionSessionId);

    /**
     * 分页获取限时购商品列表
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 限时购添加商品
     * @param relationList
     * @return
     */
    int create(List<SmsFlashPromotionProductRelation> relationList);

    /**
     * 修改关联信息
     * @param id
     * @param productRelation
     * @return
     */
    int update(Long id, SmsFlashPromotionProductRelation productRelation);

    /**
     * 删除关联信息
     * @param id
     * @return
     */
    int delete(Long id);
}
