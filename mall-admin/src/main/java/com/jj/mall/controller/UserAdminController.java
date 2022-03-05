package com.jj.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.jj.mall.dto.UmsAdminLoginParam;
import com.jj.mall.model.UmsRole;
import com.jj.mall.service.UmsRoleService;
import com.jj.mall.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台管理控制器
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Api(tags = "userAdminController",value = "后台管理")
@RestController
@RequestMapping("/admin")
public class UserAdminController {
    @Resource
    private UserAdminService adminService;

    @Resource
    private UmsRoleService roleService;

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo() {
        UmsAdmin umsAdmin = adminService.getCurrentAdmin();
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }
    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        return adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = adminService.loadUserByUsername(username);
        return userDTO;
    }

}