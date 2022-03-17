package com.jj.mall.service;

import com.jj.mall.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 商品优选Service
 * @author 任人子
 * @date 2022/3/11  - {TIME}
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取所有优选商品
     * @return
     */
    List<CmsPrefrenceArea> listAll();
}
