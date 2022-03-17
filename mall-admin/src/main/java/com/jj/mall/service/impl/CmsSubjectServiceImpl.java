package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.CmsSubjectMapper;
import com.jj.mall.model.CmsSubject;
import com.jj.mall.model.CmsSubjectExample;
import com.jj.mall.model.SmsHomeRecommendSubjectExample;
import com.jj.mall.service.CmsSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品主题ServiceImpl
 * @author 任人子
 * @date 2022/3/11  - {TIME}
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
    @Resource
    private CmsSubjectMapper subjectMapper;
    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectByExample(new CmsSubjectExample());
    }

    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andTitleLike("%" + keyword + "%");
        }
        return subjectMapper.selectByExample(example);
    }
}
