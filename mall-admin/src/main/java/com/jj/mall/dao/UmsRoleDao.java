package com.jj.mall.dao;

import com.jj.mall.model.UmsMenu;

import java.util.List;

/**
 * 自定义后台管理Dao
 * @author 任人子
 * @date 2022/3/5  - {TIME}
 */
public interface UmsRoleDao {

    /**
     * 根据后台用户ID获取菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getRoleList(Long adminId);
}
