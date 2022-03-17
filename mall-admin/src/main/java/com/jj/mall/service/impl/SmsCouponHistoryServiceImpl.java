package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.SmsCouponHistoryMapper;
import com.jj.mall.model.SmsCouponHistory;
import com.jj.mall.model.SmsCouponHistoryExample;
import com.jj.mall.model.UmsRoleResourceRelation;
import com.jj.mall.service.SmsCouponHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券领取记录 Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Resource
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Override
    public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {

        PageHelper.startPage(pageNum, pageSize);
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        SmsCouponHistoryExample.Criteria criteria = example.createCriteria();
        if(criteria != null){
            criteria.andCouponIdEqualTo(couponId);
        }
        if(useStatus != null){
            criteria.andUseStatusEqualTo(useStatus);
        }
        if(!StringUtils.isEmpty(orderSn)){
            criteria.andOrderSnEqualTo(orderSn);
        }
        return couponHistoryMapper.selectByExample(example);
    }
}
