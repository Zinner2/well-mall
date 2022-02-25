package com.jj.mall.admin.dao;

import com.jj.mall.model.UmsRole;

import java.util.List;

/**
 * 自定义后台用户和角色管理dao
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取所有角色
     * @param adminId
     * @return
     */
    List<UmsRole> getUmsAdminRoleList(Long adminId);
 }
