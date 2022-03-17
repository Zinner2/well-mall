package com.jj.mall.service.impl;

import com.jj.mall.mapper.CmsPrefrenceAreaMapper;
import com.jj.mall.model.CmsPrefrenceArea;
import com.jj.mall.model.CmsPrefrenceAreaExample;
import com.jj.mall.service.CmsPrefrenceAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品优选ServiceImpl
 * @author 任人子
 * @date 2022/3/11  - {TIME}
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Resource
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;
    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
