package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.PmsProductMapper;
import com.jj.mall.model.PmsProduct;
import com.jj.mall.model.PmsProductExample;
import com.jj.mall.service.PmsProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台商品管理ServiceImpl
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Resource
    private PmsProductMapper productMapper;
    @Override
    public List<PmsProduct> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        example.setOrderByClause("sort desc");
        return productMapper.selectByExample(example);
    }
}
