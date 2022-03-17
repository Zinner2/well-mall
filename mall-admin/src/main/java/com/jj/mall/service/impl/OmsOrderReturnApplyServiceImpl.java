package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.OmsOrderReturnApplyDao;
import com.jj.mall.dto.OmsOrderReturnApplyResult;
import com.jj.mall.dto.OmsReturnApplyQueryParam;
import com.jj.mall.dto.OmsUpdateStatusParam;
import com.jj.mall.mapper.OmsOrderReturnApplyMapper;
import com.jj.mall.model.OmsOrder;
import com.jj.mall.model.OmsOrderReturnApply;
import com.jj.mall.model.OmsOrderReturnApplyExample;
import com.jj.mall.service.OmsOrderReturnApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单退货申请 ServiceImpl
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
    @Resource
    private OmsOrderReturnApplyDao orderReturnApplyDao;
    @Resource
    private OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        return omsOrderReturnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnApplyExample example = new OmsOrderReturnApplyExample();
        example.createCriteria().andIdIn(ids).andStatusEqualTo(3);
        return omsOrderReturnApplyMapper.deleteByExample(example);
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return orderReturnApplyDao.getDetail(id);
    }

    @Override
    public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderReturnApplyDao.getList(queryParam);
    }
}
