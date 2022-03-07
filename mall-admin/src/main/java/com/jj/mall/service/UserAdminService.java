package com.jj.mall.service;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.dto.UmsAdminParam;
import com.jj.mall.model.UmsAdmin;
import com.jj.mall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  后台管理Service
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
public interface UserAdminService {

    /**
     * 获取角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     *  获取用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    CommonResult login(String username, String password);

    /**
     * 获取当前登录用户信息
     * @return
     */
    UmsAdmin getCurrentAdmin();

    /**
     * 分页查询用户列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 用户注册
     * @param adminParam
     * @return
     */
    UmsAdmin register(UmsAdminParam adminParam);

    /**
     * 修改用户状态
     * @param umsAdmin
     * @return
     */
    int update(UmsAdmin umsAdmin);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 给用户分配角色
     * @param adminId
     * @param roleIds
     * @return
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 修改用户状态
     * @param umsAdmin
     * @return
     */
    int updateStatus(UmsAdmin umsAdmin);
}
