package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.PmsProductCategoryDao;
import com.jj.mall.dto.PmsProductCategoryWithChildrenItem;
import com.jj.mall.mapper.PmsProductCategoryMapper;
import com.jj.mall.model.PmsProductCategory;
import com.jj.mall.model.PmsProductCategoryExample;
import com.jj.mall.service.PmsProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台商品目录ServiceImpl
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private PmsProductCategoryDao productCategoryDao;
    @Override
    public List<PmsProductCategoryWithChildrenItem> withChildren() {
        return productCategoryDao.withChildren();
    }

    @Override
    public List<PmsProductCategory> list(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }
}
