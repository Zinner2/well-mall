package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.service.RedisService;
import com.jj.mall.mapper.UmsResourceMapper;
import com.jj.mall.mapper.UmsRoleMapper;
import com.jj.mall.mapper.UmsRoleResourceRelationMapper;
import com.jj.mall.model.*;
import com.jj.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 后台资源管理ServiceImpl
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

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
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }

    @Override
    public int updateResource(Long id, UmsResource resource) {
        resource.setId(id);
        int count = resourceMapper.updateByPrimaryKeySelective(resource);
        initResourcesRolesMap();
        return count;
    }

    @Override
    public int deleteResource(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        initResourcesRolesMap();
        return count;
    }

    @Override
    public int createResource(UmsResource resource) {
        resource.setCreateTime(new Date());
        int result = resourceMapper.insert(resource);
        initResourcesRolesMap();
        return result;
    }

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

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        UmsResourceExample resourceExample = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = resourceExample.createCriteria();
        if(!StringUtils.isEmpty(nameKeyword)){
            criteria.andNameLike("%" + nameKeyword + "%");
        }else if(!StringUtils.isEmpty(urlKeyword)){
            criteria.andUrlLike("%" + urlKeyword + "%");
        }else if(!StringUtils.isEmpty(categoryId)){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        return resourceMapper.selectByExample(resourceExample);
    }
}
