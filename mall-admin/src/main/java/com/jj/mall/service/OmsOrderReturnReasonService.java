package com.jj.mall.service;

import com.jj.mall.model.OmsOrderReturnReason;

import java.util.List;

/**
 * 退货原因管理 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsOrderReturnReasonService {
    /**
     * 分页获取退货原因列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

    /**
     * 新建退货原因
     * @param returnReason
     * @return
     */
    int create(OmsOrderReturnReason returnReason);

    /**
     * 修改退货原因
     * @param id
     * @param returnReason
     * @return
     */
    int update(Long id, OmsOrderReturnReason returnReason);

    /**
     * 删除退货原因列表
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 获取单个退货原因详情信息
     * @param id
     * @return
     */
    OmsOrderReturnReason getItem(Long id);

    /**
     * 修改退货原因启用状态
     * @param ids
     * @param status
     * @return
     */
    int updateStatus(List<Long> ids, Integer status);
}
