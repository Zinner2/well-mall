package com.jj.mall.component;

import com.jj.mall.service.UmsResourcesService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 资源权限信息初始化
 * @author 任人子
 * @date 2022/2/27  - {TIME}
 */
@Component
public class ResourcesRolesRulesHandle {

    @Resource
    private UmsResourcesService resourceService;

    @PostConstruct
    public void initResourceRolesMap(){
        resourceService.initResourcesRolesMap();
    }
}