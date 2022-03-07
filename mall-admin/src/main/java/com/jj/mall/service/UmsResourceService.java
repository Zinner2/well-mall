package com.jj.mall.service;

import com.jj.mall.model.UmsResource;

import java.util.List;
import java.util.Map;

/**
 * 后台资源管理Service
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
public interface UmsResourceService {

    /**
     * 初始化资源角色规则
     * @return
     */
    Map<String, List<String>> initResourcesRolesMap();
    /**
     * 分页获取资源列表
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize);

    /**
     * 创建后台资源
     * @param resource
     * @return
     */
    int createResource(UmsResource resource);

    /**
     * 删除后台资源
     * @param id
     * @return
     */
    int deleteResource(Long id);

    /**
     * 修改后台资源
     * @param id
     * @param resource
     * @return
     */
    int updateResource(Long id, UmsResource resource);
}
