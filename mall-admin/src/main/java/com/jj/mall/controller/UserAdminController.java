package com.jj.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.jj.mall.common.api.CommonPage;
import com.jj.mall.dto.UmsAdminLoginParam;
import com.jj.mall.dto.UmsAdminParam;
import com.jj.mall.model.UmsRole;
import com.jj.mall.service.UmsRoleService;
import com.jj.mall.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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



    @ApiOperation(value = "给用户分配角色")
    @PostMapping("/role/update")
    public CommonResult<Integer> updateRole(@RequestParam("adminId") Long adminId,
                                            @RequestParam("roleIds") List<Long> roleIds){
        int count = adminService.updateRole(adminId, roleIds);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id){
        int count = adminService.delete(id);
        if(count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody UmsAdmin umsAdmin){
        umsAdmin.setId(id);
        int result = adminService.update(umsAdmin);
        if(result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改用户状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult<Integer> updateStatus(@PathVariable Long id, @RequestParam("status") Integer status){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(id);
        umsAdmin.setStatus(status);
        int count = adminService.updateStatus(umsAdmin);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam adminParam) {
        UmsAdmin admin = adminService.register(adminParam);
        if(admin == null){
            return CommonResult.failed();
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "获取角色指定用户")
    @GetMapping("/role/{adminId}")
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId){
        List<UmsRole> roles = adminService.getRoleList(adminId);
        return CommonResult.success(roles);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping("/logout")
    public CommonResult logout(){
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        List<UmsAdmin> adminList = adminService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(adminList));
    }
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
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
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        return adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = adminService.loadUserByUsername(username);
        return userDTO;
    }

}
