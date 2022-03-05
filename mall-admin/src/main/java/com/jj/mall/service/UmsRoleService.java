package com.jj.mall.service;

import com.jj.mall.model.UmsMenu;

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
}
