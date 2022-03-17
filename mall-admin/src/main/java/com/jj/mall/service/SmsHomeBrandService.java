package com.jj.mall.service;

import com.jj.mall.model.SmsHomeBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页品牌管理Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsHomeBrandService {
    /**
     * 分页查询品牌列表
     * @param brandName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    /**
     * 添加首页推荐品牌
     * @param homeBrandList
     * @return
     */
    @Transactional
    int create(List<SmsHomeBrand> homeBrandList);

    /**
     * 修改品牌排序
     * @param id
     * @param sort
     * @return
     */
    int updateSort(Long id, Integer sort);

    /**
     * 批量删除推荐品牌
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 批量修改推荐状态
     * @param ids
     * @param recommendStatus
     * @return
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
}
