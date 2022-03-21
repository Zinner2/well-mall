package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.SmsFlashPromotionMapper;
import com.jj.mall.model.SmsFlashPromotion;
import com.jj.mall.model.SmsFlashPromotionExample;
import com.jj.mall.service.SmsFlashPromotionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 限时购活动管理ServiceImpl
 *
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
    @Resource
    private SmsFlashPromotionMapper flashPromotionMapper;

    @Override
    public int updateStatusFlashPromotion(Long id, Integer status) {
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setId(id);
        promotion.setStatus(status);
        return flashPromotionMapper.updateByPrimaryKeySelective(promotion);
    }

    @Override
    public int deleteFlash(Long id) {
        return flashPromotionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateFlash(Long id, SmsFlashPromotion promotion) {
        promotion.setId(id);
        return flashPromotionMapper.updateByPrimaryKey(promotion);
    }

    @Override
    public int createFlash(SmsFlashPromotion promotion) {
        promotion.setCreateTime(new Date());
        return flashPromotionMapper.insert(promotion);
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andTitleLike("%" + keyword + "%");
        }
        return flashPromotionMapper.selectByExample(example);
    }

}
