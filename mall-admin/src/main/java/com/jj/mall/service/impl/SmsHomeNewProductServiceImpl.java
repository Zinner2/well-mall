package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.SmsHomeNewProductMapper;
import com.jj.mall.model.SmsHomeNewProduct;
import com.jj.mall.model.SmsHomeNewProductExample;
import com.jj.mall.service.SmsHomeNewProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页新品管理ServiceImpl
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {
    @Resource
    private SmsHomeNewProductMapper homeNewProductMapper;

    @Override
    public int create(List<SmsHomeNewProduct> homeBrandList) {
        for (SmsHomeNewProduct product : homeBrandList) {
            product.setSort(0);
            product.setRecommendStatus(1);
            homeNewProductMapper.insert(product);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeNewProductMapper.updateByPrimaryKeySelective(homeNewProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        return homeNewProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setRecommendStatus(recommendStatus);
        return homeNewProductMapper.updateByExampleSelective(homeNewProduct, example);
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeNewProductExample example = new SmsHomeNewProductExample();
        SmsHomeNewProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(productName)){
            criteria.andProductNameLike("%" + productName + "%");
        }
        if(recommendStatus != null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return homeNewProductMapper.selectByExample(example);
    }
}
