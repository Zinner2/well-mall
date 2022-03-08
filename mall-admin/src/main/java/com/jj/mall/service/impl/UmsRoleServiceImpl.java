package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.UmsRoleDao;
import com.jj.mall.mapper.UmsRoleMapper;
import com.jj.mall.mapper.UmsRoleMenuRelationMapper;
import com.jj.mall.mapper.UmsRoleResourceRelationMapper;
import com.jj.mall.model.*;
import com.jj.mall.service.UmsResourceService;
import com.jj.mall.service.UmsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 后台角色管理ServiceImpl
 *
 * @author 任人子
 * @date 2022/3/5  - {TIME}
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Resource
    private UmsRoleDao roleDao;
    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsResourceService resourceService;
    @Resource
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        for(Long menuId : menuIds){
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setMenuId(menuId);
            relation.setRoleId(roleId);
            roleMenuRelationMapper.insert(relation);
        }
        resourceService.initResourcesRolesMap();
        return menuIds.size();
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceList) {
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        // 先删除已有关系
        roleResourceRelationMapper.deleteByExample(example);
        // 添加新的关系
        for(Long resourceId : resourceList){
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setResourceId(resourceId);
            relation.setRoleId(roleId);
            roleResourceRelationMapper.insert(relation);
        }
        resourceService.initResourcesRolesMap();
        return resourceList.size();
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleDao.getResourceByRoleId(roleId);
    }

    @Override
    public int updateRole(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteRoles(List<Long> ids) {
        UmsRoleExample menuExample = new UmsRoleExample();
        menuExample.createCriteria().andIdIn(ids);
        int count = roleMapper.deleteByExample(menuExample);
        resourceService.initResourcesRolesMap();
        return count;
    }

    @Override
    public int createRole(UmsRole role) {
        role.setCreateTime(new Date());
        return roleMapper.insert(role);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getRoleList(adminId);
    }

    @Override
    public List<UmsRole> listAll() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample roleExample = new UmsRoleExample();
        UmsRoleExample.Criteria criteria = roleExample.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(roleExample);
    }

}
