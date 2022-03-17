package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dto.PmsBrandParam;
import com.jj.mall.mapper.PmsBrandMapper;
import com.jj.mall.mapper.PmsProductMapper;
import com.jj.mall.model.PmsBrand;
import com.jj.mall.model.PmsBrandExample;
import com.jj.mall.model.PmsProduct;
import com.jj.mall.model.PmsProductExample;
import com.jj.mall.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Resource
    private PmsProductMapper pmsProductMapper;
    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return pmsBrandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return pmsBrandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
    }

    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int update(Long id, PmsBrandParam brandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andBrandIdEqualTo(id);
        pmsProductMapper.updateByExampleSelective(product,example);
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public PmsBrand getBrandId(Long id) {
        PmsBrandExample example = new PmsBrandExample();
        example.createCriteria().andIdEqualTo(id);
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsBrandExample example = new PmsBrandExample();
        example.setOrderByClause("sort desc");
        return pmsBrandMapper.selectByExample(example);
    }
}
