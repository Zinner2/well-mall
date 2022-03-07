package com.jj.mall.service;

import com.jj.mall.model.UmsMenu;

import java.util.List;

/**
 * 后台菜单管理Service
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
public interface UmsMenuService {

    /**
     * 分页获取菜单列表
     * @param parentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 添加后台菜单
     * @param umsMenu
     * @return
     */
    int createMenu(UmsMenu umsMenu);

    /**
     * 根据id获取后台菜单
     * @param id
     * @return
     */
    UmsMenu getItem(Long id);

    /**
     * 删除指定菜单
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     * @return
     */
    int updateMenu(Long id, UmsMenu umsMenu);
}
