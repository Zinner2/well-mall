package com.jj.mall.service;

import com.jj.mall.dto.OmsOrderReturnApplyResult;
import com.jj.mall.dto.OmsReturnApplyQueryParam;
import com.jj.mall.dto.OmsUpdateStatusParam;
import com.jj.mall.model.OmsOrderReturnApply;

import java.util.List;

/**
 * 订单退货申请 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页获取退货申请列表
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 获取退货申请详情
     * @param id
     * @return
     */
    OmsOrderReturnApplyResult getItem(Long id);

    /**
     * 批量删除申请
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     *
     * @param id
     * @param statusParam
     * @return
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);
}
