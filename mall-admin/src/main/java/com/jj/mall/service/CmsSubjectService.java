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

    /**
     * 根据专题名称分页获取专题
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}
