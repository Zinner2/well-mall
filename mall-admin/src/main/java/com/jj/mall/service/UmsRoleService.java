package com.jj.mall.service;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsRole;

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
}
