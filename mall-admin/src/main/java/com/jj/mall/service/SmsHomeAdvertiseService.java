package com.jj.mall.service;

import com.jj.mall.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页轮播广告管理Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsHomeAdvertiseService {
    /**
     * 分页查询广告列表
     * @param name
     * @param type
     * @param endTime
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);

    /**
     * 修改广告
     * @param id
     * @param advertise
     * @return
     */
    int update(Long id, SmsHomeAdvertise advertise);

    /**
     * 获取广告详情
     * @param id
     * @return
     */
    SmsHomeAdvertise getItem(Long id);

    /**
     * 修改上下线状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除广告
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 新建广告
     * @param advertise
     * @return
     */
    int create(SmsHomeAdvertise advertise);
}
