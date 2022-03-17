package com.jj.mall.service;

import com.jj.mall.model.SmsHomeRecommendSubject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  * 首页专题推荐管理Service
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
public interface SmsHomeRecommendSubjectService {
    /**
     * 分页查询推荐
     * @param subjectName
     * @param recommendStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum);

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
     * 添加首页推荐专题
     * @param homeBrandList
     * @return
     */
    @Transactional
    int create(List<SmsHomeRecommendSubject> homeBrandList);
}
