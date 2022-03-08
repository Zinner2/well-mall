package com.jj.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.jj.mall.dto.UmsMenuNode;
import com.jj.mall.mapper.UmsMenuMapper;
import com.jj.mall.model.UmsAdminExample;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsMenuExample;
import com.jj.mall.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuMapper.selectByExample(new UmsMenuExample());
        List<UmsMenuNode> menuNodeList = menuList.stream()
                                        .filter(item -> item.getParentId().equals(0L))
                                        .map(item -> convertMenuNode(item, menuList)).collect(Collectors.toList());
        return menuNodeList;
    }

    /**
     * 将 UmsMenu 转换为 UmsMenuNode 并设置 Children
     * @param menu
     * @param menuList
     * @return
     */
    public UmsMenuNode convertMenuNode(UmsMenu menu, List<UmsMenu> menuList){
        UmsMenuNode menuNode = new UmsMenuNode();
        BeanUtils.copyProperties(menu, menuNode);
        List<UmsMenuNode> children = menuList.stream()
                                        .filter(item -> menuNode.getId().equals(item.getParentId()))
                                        .map(item -> convertMenuNode(item, menuList)).collect(Collectors.toList());
        menuNode.setChildren(children);
        return menuNode;
    }
    @Override
    public int updateHidden(Long id, Integer hidden) {
        hidden = hidden == 1 ? 0 : 1;
        UmsMenu menu = new UmsMenu();
        menu.setId(id);
        menu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

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
