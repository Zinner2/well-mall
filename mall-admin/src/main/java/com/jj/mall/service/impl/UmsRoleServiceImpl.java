package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dao.UmsRoleDao;
import com.jj.mall.mapper.UmsRoleMapper;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsRole;
import com.jj.mall.model.UmsRoleExample;
import com.jj.mall.service.UmsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
