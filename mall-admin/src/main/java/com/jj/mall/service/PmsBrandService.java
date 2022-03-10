package com.jj.mall.service;

import com.jj.mall.model.PmsBrand;

import java.util.List;

/**
 * 后台品牌管理Service
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsBrandService {
    /**
     * 分页获取后台品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> list(Integer pageNum, Integer pageSize);
}
