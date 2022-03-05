package com.jj.mall.service.impl;

import com.jj.mall.service.UmsResourcesService;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.service.RedisService;
import com.jj.mall.mapper.UmsResourceMapper;
import com.jj.mall.mapper.UmsRoleMapper;
import com.jj.mall.mapper.UmsRoleResourceRelationMapper;
import com.jj.mall.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author 任人子
 * @date 2022/2/27  - {TIME}
 */
@Service
public class UmsResourcesServiceImpl implements UmsResourcesService {
    @Resource
    private UmsResourceMapper resourceMapper;
    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Resource
    private RedisService redisService;
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public Map<String, List<String>> initResourcesRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<UmsResource> resourceList = resourceMapper.selectByExample(new UmsResourceExample());
        List<UmsRole> roleList = roleMapper.selectByExample(new UmsRoleExample());
        List<UmsRoleResourceRelation> relationList = roleResourceRelationMapper.selectByExample(new UmsRoleResourceRelationExample());
        for (UmsResource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(UmsRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/"+applicationName+resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;

    }
}
