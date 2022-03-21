package com.jj.mall.service;

import com.jj.mall.dto.SmsFlashPromotionSessionDetail;
import com.jj.mall.model.SmsFlashPromotionSession;

import java.util.List;

/**
 * 限时购场次管理Service
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
public interface SmsFlashPromotionSessionService {
    /**
     * 获取所有场次
     * @return
     */
    List<SmsFlashPromotionSession> getList();

    /**
     * 添加场次
     * @param flashPromotionSession
     * @return
     */
    int create(SmsFlashPromotionSession flashPromotionSession);

    /**
     * 修改场次
     * @param id
     * @param flashPromotionSession
     * @return
     */
    int update(Long id, SmsFlashPromotionSession flashPromotionSession);

    /**
     * 删除场次
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改场次状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取该活动所有场次信息及商品数量
     * @param id
     * @return
     */
    List<SmsFlashPromotionSessionDetail> selectAll(Long id);
}
