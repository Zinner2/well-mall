package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.PmsBrandMapper;
import com.jj.mall.model.PmsBrand;
import com.jj.mall.model.PmsBrandExample;
import com.jj.mall.service.PmsBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台品牌管理ServiceImpl
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Resource
    private PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample example = new PmsBrandExample();
        example.setOrderByClause("sort desc");
        return pmsBrandMapper.selectByExample(example);
    }
}
