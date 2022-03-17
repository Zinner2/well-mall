package com.jj.mall.service;

import com.jj.mall.model.CmsSubject;

import java.util.List;

/**
 * 商品主题Service
 * @author 任人子
 * @date 2022/3/11  - {TIME}
 */
public interface CmsSubjectService {
    /**
     * 获取所有商品主题
     * @return
     */
    List<CmsSubject> listAll();
}
