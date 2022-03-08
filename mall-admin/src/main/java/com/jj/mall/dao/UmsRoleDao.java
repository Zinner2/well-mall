package com.jj.mall.dao;

import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

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
    List<UmsMenu> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取角色相关资源
     * @param roleId
     * @return
     */
    List<UmsResource> getResourceByRoleId(@Param("roleId")Long roleId);

    /**
     * 获取角色相关菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
}
