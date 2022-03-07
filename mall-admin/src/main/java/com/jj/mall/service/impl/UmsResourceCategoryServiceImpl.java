package com.jj.mall.service.impl;

import com.jj.mall.mapper.UmsResourceCategoryMapper;
import com.jj.mall.model.UmsResourceCategory;
import com.jj.mall.model.UmsResourceCategoryExample;
import com.jj.mall.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 后台资源目录管理ServiceImpl
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Resource
    private UmsResourceCategoryMapper resourceCategoryMapper;

    @Override
    public int deleteCategory(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCategory(Long id, UmsResourceCategory resourceCategory) {
        resourceCategory.setId(id);
        return resourceCategoryMapper.updateByPrimaryKeySelective(resourceCategory);
    }

    @Override
    public int createCategory(UmsResourceCategory resourceCategory) {
        resourceCategory.setCreateTime(new Date());
        return resourceCategoryMapper.insert(resourceCategory);
    }

    @Override
    public List<UmsResourceCategory> listAllCategories() {
        return resourceCategoryMapper.selectByExample(new UmsResourceCategoryExample());
    }
}
