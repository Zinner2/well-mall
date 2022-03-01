package com.jj.mall.admin.service;

import java.util.List;
import java.util.Map;

/**
 * 后台资源管理Service
 * @author 任人子
 * @date 2022/2/27  - {TIME}
 */
public interface UmsResourcesService {

    /**
     * 初始化资源角色规则
     * @return
     */
    Map<String, List<String>> initResourcesRolesMap();
}
