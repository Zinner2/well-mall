package com.jj.mall.service;

import com.jj.mall.model.SmsFlashPromotion;

import java.util.List;

/**
 * 限时购活动管理Service
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
public interface SmsFlashPromotionService {
    /**
     * 分页获取限时活动列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 新建限时活动
     * @param promotion
     * @return
     */
    int createFlash(SmsFlashPromotion promotion);

    /**
     * 修改限时活动
     * @param id
     * @param promotion
     * @return
     */
    int updateFlash(Long id, SmsFlashPromotion promotion);

    /**
     * 删除限时活动
     * @param id
     * @return
     */
    int deleteFlash(Long id);

    /**
     * 修改上下线状态
     * @param id
     * @param status
     * @return
     */
    int updateStatusFlashPromotion(Long id, Integer status);
}
