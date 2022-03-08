package com.jj.mall.service;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsResource;
import com.jj.mall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 * @author 任人子
 * @date 2022/3/5  - {TIME}
 */
public interface UmsRoleService {

    /**
     * 根据管理员Id获取对应菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取所有角色
     * @return
     */
    List<UmsRole> listAll();

    /**
     * 分页获取角色列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsRole> list(String keyword, Integer pageNum, Integer pageSize);


    /**
     * 添加后台角色
     * @param role
     * @return
     */
    int createRole(UmsRole role);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    int deleteRoles(List<Long> ids);

    /**
     * 修改角色信息
     * @param id
     * @param role
     * @return
     */
    int updateRole(Long id, UmsRole role);

    /**
     * 获取角色可用资源
     * @param id
     * @return
     */
    List<UmsResource> listResource(Long id);

    /**
     * 给角色分配资源
     * @param roleId
     * @param resourceList
     * @return
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceList);

    /**
     * 角色可分配菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> listMenu(Long roleId);


    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    int allocMenu(Long roleId, List<Long> menuIds);
}
