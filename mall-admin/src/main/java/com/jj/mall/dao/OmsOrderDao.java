package com.jj.mall.dao;

import com.jj.mall.dto.OmsOrderDeliveryParam;
import com.jj.mall.dto.OmsOrderDetail;
import com.jj.mall.dto.OmsOrderQueryParam;
import com.jj.mall.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单管理 Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsOrderDao {
    /**
     * 获取订单列表
     * @param queryParam
     * @return
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 获取获取订单详情
     * @param id
     * @return
     */
    OmsOrderDetail getDetail(@Param("id") Long id);

    /**
     * 批量发货订单
     * @param deliveryParamList
     * @return
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);
}
