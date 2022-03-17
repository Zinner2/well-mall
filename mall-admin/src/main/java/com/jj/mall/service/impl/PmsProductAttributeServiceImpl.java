package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.PmsProductAttributeDao;
import com.jj.mall.dto.PmsProductAttributeParam;
import com.jj.mall.dto.ProductAttrInfo;
import com.jj.mall.mapper.PmsProductAttributeCategoryMapper;
import com.jj.mall.mapper.PmsProductAttributeMapper;
import com.jj.mall.model.PmsProduct;
import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductAttributeCategory;
import com.jj.mall.model.PmsProductAttributeExample;
import com.jj.mall.service.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台上商品参数ServiceImpl
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@RestController
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Resource
    private PmsProductAttributeMapper pmsProductAttributeMapper;
    @Resource
    private PmsProductAttributeDao productAttributeDao;
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public int delete(List<Long> ids) {
        //获取分类
        PmsProductAttribute pmsProductAttribute = pmsProductAttributeMapper.selectByPrimaryKey(ids.get(0));
        Integer type = pmsProductAttribute.getType();
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.createCriteria().andIdIn(ids);
        int count = pmsProductAttributeMapper.deleteByExample(example);
        //删除完成后修改数量
        if(type==0){
            if(pmsProductAttributeCategory.getAttributeCount()>=count){
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
            }else{
                pmsProductAttributeCategory.setAttributeCount(0);
            }
        }else if(type==1){
            if(pmsProductAttributeCategory.getParamCount()>=count){
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
            }else{
                pmsProductAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }
    @Override
    public PmsProductAttribute getItem(Long id) {
        return pmsProductAttributeMapper.selectByPrimaryKey(id);
    }
    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, pmsProductAttribute);
        return pmsProductAttributeMapper.updateByPrimaryKeySelective(pmsProductAttribute);
    }

    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam, pmsProductAttribute);
        int count = pmsProductAttributeMapper.insertSelective(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }

    @Override
    public List<PmsProductAttribute> list(Long id, Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        PmsProductAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andProductAttributeCategoryIdEqualTo(id)
                .andTypeEqualTo(type);
        example.setOrderByClause("sort desc");
        return pmsProductAttributeMapper.selectByExample(example);
    }


    @Override
    public List<PmsProductAttribute> category(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return pmsProductAttributeMapper.selectByExample(new PmsProductAttributeExample());
    }



}
