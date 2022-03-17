package com.jj.mall.service.impl;

import com.jj.mall.mapper.CmsSubjectMapper;
import com.jj.mall.model.CmsSubject;
import com.jj.mall.model.CmsSubjectExample;
import com.jj.mall.service.CmsSubjectService;
import org.springframework.stereotype.Service;

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
}
