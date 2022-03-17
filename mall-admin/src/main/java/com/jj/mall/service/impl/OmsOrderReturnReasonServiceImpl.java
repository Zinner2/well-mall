package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.common.utils.DataUtils;
import com.jj.mall.mapper.OmsOrderReturnReasonMapper;
import com.jj.mall.model.OmsOrderReturnReason;
import com.jj.mall.model.OmsOrderReturnReasonExample;
import com.jj.mall.service.OmsOrderReturnReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分页获取退货原因 ServiceImpl
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Resource
    private OmsOrderReturnReasonMapper orderReturnReasonMapper;

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return orderReturnReasonMapper.updateByExampleSelective(record,example);
    }

    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return orderReturnReasonMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return orderReturnReasonMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return orderReturnReasonMapper.updateByPrimaryKeySelective(returnReason);
    }

    @Override
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(DataUtils.getLocalCurrentTime());
        return orderReturnReasonMapper.insert(returnReason);
    }

    @Override
    public List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.setOrderByClause("sort desc");
        return orderReturnReasonMapper.selectByExample(example);
    }
}
