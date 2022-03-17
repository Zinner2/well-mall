package com.jj.mall.service;

import com.jj.mall.model.SmsHomeRecommendProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页人气推荐Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */

public interface SmsHomeRecommendProductService {
    /**
     * 分页查询推荐
     * @param productName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    /**
     * 批量修改推荐状态
     * @param ids
     * @param recommendStatus
     * @return
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量删除推荐
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 修改推荐排序
     * @param id
     * @param sort
     * @return
     */
    int updateSort(Long id, Integer sort);

    /**
     * 添加首页推荐
     * @param homeBrandList
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    int create(List<SmsHomeRecommendProduct> homeBrandList);
}
