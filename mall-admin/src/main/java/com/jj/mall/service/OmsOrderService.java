package com.jj.mall.service;

import com.jj.mall.dto.OmsOrderDeliveryParam;
import com.jj.mall.dto.OmsOrderDetail;
import com.jj.mall.dto.OmsOrderQueryParam;
import com.jj.mall.dto.OmsReceiverInfoParam;
import com.jj.mall.model.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单管理Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsOrderService {
    /**
     * 分页获取订单列表
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 获取指定订单详细信息
     * @param id
     * @return
     */
    OmsOrderDetail detail(Long id);

    /**
     * 批量发货
     * @param deliveryParamList
     * @return
     */
    @Transactional
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     * @param ids
     * @param note
     * @return
     */
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 修改收货人信息
     * @param receiverInfoParam
     * @return
     */
    @Transactional
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);
}
