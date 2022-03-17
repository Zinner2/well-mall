package com.jj.mall.dao;

import com.jj.mall.dto.OmsOrderReturnApplyResult;
import com.jj.mall.dto.OmsReturnApplyQueryParam;
import com.jj.mall.model.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请 Dao
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface OmsOrderReturnApplyDao {
    /**
     * 获取退货申请列表
     * @param queryParam
     * @return
     */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     * @param id
     * @return
     */
    OmsOrderReturnApplyResult getDetail(@Param("id") Long id);
}
