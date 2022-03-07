package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.mapper.UmsMenuMapper;
import com.jj.mall.model.UmsAdminExample;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsMenuExample;
import com.jj.mall.service.UmsMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 后台菜单管理Service
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Resource
    private UmsMenuMapper menuMapper;

    @Override
    public int updateMenu(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return menuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsMenu getItem(Long id) {
        UmsMenuExample menuExample = new UmsMenuExample();
        menuExample.createCriteria().andIdEqualTo(id);
        List<UmsMenu> menuList = menuMapper.selectByExample(menuExample);
        if(menuList.size() > 0){
            return menuList.get(0);
        }
        return null;
    }

    @Override
    public int createMenu(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return menuMapper.insert(umsMenu);
    }

    /**
     * 更新菜单级别
     * @param umsMenu
     */
    private void updateLevel(UmsMenu umsMenu) {
        if(umsMenu.getParentId() == 0){
            // 如果没有父级菜单
            umsMenu.setLevel(0);
        }else{
            // 如果有父级菜单,查询并更新
            UmsMenu fatherMenu = menuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if(fatherMenu != null){
                umsMenu.setLevel(fatherMenu.getLevel() + 1);
            }else{
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample menuExample = new UmsMenuExample();
        menuExample.setOrderByClause("sort desc");
        menuExample.createCriteria().andParentIdEqualTo(parentId);
        return menuMapper.selectByExample(menuExample);
    }

}
