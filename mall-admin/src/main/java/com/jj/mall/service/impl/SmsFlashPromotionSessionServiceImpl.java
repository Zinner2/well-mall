package com.jj.mall.service.impl;

import com.jj.mall.common.utils.DataUtils;
import com.jj.mall.dto.SmsFlashPromotionSessionDetail;
import com.jj.mall.mapper.SmsFlashPromotionSessionMapper;
import com.jj.mall.model.SmsFlashPromotionProductRelation;
import com.jj.mall.model.SmsFlashPromotionSession;
import com.jj.mall.model.SmsFlashPromotionSessionExample;
import com.jj.mall.service.SmsFlashPromotionProductRelationService;
import com.jj.mall.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 限时购场次管理ServiceImpl
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {
    @Resource
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Resource
    private SmsFlashPromotionProductRelationService relationService;
    @Override
    public List<SmsFlashPromotionSessionDetail> selectAll(Long id) {
        List<SmsFlashPromotionSessionDetail> res = new ArrayList<>();
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria().andStatusEqualTo(1);
        List<SmsFlashPromotionSession> flashPromotionSessionList = flashPromotionSessionMapper.selectByExample(example);
        for(SmsFlashPromotionSession session : flashPromotionSessionList){
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(session, detail);
            Long count = relationService.getCount(id, session.getId());
            detail.setProductCount(count);
            res.add(detail);
        }
        return res;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession record = new SmsFlashPromotionSession();
        record.setId(id);
        record.setStatus(status);
        return flashPromotionSessionMapper.updateByPrimaryKey(record);
    }

    @Override
    public int delete(Long id) {
        return flashPromotionSessionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSession flashPromotionSession) {
        flashPromotionSession.setId(id);
        return flashPromotionSessionMapper.updateByPrimaryKey(flashPromotionSession);
    }

    @Override
    public int create(SmsFlashPromotionSession flashPromotionSession) {
        flashPromotionSession.setCreateTime(DataUtils.getLocalCurrentTime());
        return flashPromotionSessionMapper.insert(flashPromotionSession);
    }

    /**
     * @return
     */
    @Override
    public List<SmsFlashPromotionSession> getList() {
        return flashPromotionSessionMapper.selectByExample(new SmsFlashPromotionSessionExample());
    }
}
