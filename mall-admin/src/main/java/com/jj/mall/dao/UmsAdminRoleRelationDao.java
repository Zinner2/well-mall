package com.jj.mall.dao;

import com.jj.mall.model.UmsResource;
import com.jj.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台用户和角色管理dao
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);
 }
