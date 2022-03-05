package com.jj.mall.service.impl;

import com.jj.mall.dao.UmsRoleDao;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.service.UmsRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台角色管理ServiceImpl
 * @author 任人子
 * @date 2022/3/5  - {TIME}
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Resource
    private UmsRoleDao roleList;

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleList.getRoleList(adminId);
    }
}
