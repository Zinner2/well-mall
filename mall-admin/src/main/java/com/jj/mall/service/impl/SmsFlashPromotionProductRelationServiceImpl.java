package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.SmsFlashPromotionProductRelationDao;
import com.jj.mall.dto.SmsFlashPromotionProduct;
import com.jj.mall.mapper.SmsFlashPromotionProductRelationMapper;
import com.jj.mall.model.SmsFlashPromotionProductRelation;
import com.jj.mall.model.SmsFlashPromotionProductRelationExample;
import com.jj.mall.service.SmsFlashPromotionProductRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限时购和商品关系管理ServiceImpl
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Resource
    private SmsFlashPromotionProductRelationMapper flashPromotionProductRelationMapper;
    @Resource
    private SmsFlashPromotionProductRelationDao flashPromotionProductRelationDao;

    @Override
    public int delete(Long id) {
        return flashPromotionProductRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation productRelation) {
        productRelation.setId(id);
        return flashPromotionProductRelationMapper.updateByPrimaryKey(productRelation);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        for(SmsFlashPromotionProductRelation relation : relationList){
            flashPromotionProductRelationMapper.insert(relation);
        }
        return relationList.size();
    }

    @Override
    public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return flashPromotionProductRelationDao.getList(flashPromotionId, flashPromotionSessionId);
    }

    @Override
    public Long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        SmsFlashPromotionProductRelationExample example  = new SmsFlashPromotionProductRelationExample();
        example.createCriteria().andFlashPromotionIdEqualTo(flashPromotionId)
                                .andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
        return flashPromotionProductRelationMapper.countByExample(example);
    }
}
