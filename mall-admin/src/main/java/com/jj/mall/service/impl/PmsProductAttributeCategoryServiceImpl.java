package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.PmsProductAttributeCategoryDao;
import com.jj.mall.dto.PmsProductAttributeCategoryItem;
import com.jj.mall.mapper.PmsProductAttributeCategoryMapper;
import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductAttributeCategory;
import com.jj.mall.model.PmsProductAttributeCategoryExample;
import com.jj.mall.service.PmsProductAttributeCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品属性分类ServiceImpl
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Resource
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
    }

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }
    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }
    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }
}
