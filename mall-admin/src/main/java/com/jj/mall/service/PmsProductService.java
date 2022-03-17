package com.jj.mall.service;

import com.jj.mall.dto.PmsProductParam;
import com.jj.mall.dto.PmsProductQueryParam;
import com.jj.mall.dto.PmsProductResult;
import com.jj.mall.model.PmsProduct;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台商品管理Service
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
public interface PmsProductService {

    /**
     * 分页查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageNum, Integer pageSize);

    /**
     * 创建商品
     * @param productParam
     * @return
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    int create(PmsProductParam productParam);

    /**
     * 批量修改商品上架状态
     * @param ids
     * @param publishStatus
     * @return
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量推荐商品
     * @param ids
     * @param recommendStatus
     * @return
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量设为新品
     * @param ids
     * @param newStatus
     * @return
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量修改删除状态
     * @param ids
     * @param deleteStatus
     * @return
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * 更新商品
     * @param id
     * @param productParam
     * @return
     */
    @Transactional
    int update(Long id, PmsProductParam productParam);
}
