package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.model.UmsResource;
import com.jj.mall.model.UmsRole;
import com.jj.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户角色管理
 *
 * @author 任人子
 * @date 2022/3/6  - {TIME}
 */
@RestController()
@RequestMapping("/role")
@Api(tags = "umsRoleController", value = "后台角色管理")
public class UmsRoleController {

    @Resource
    private UmsRoleService roleService;

    @ApiOperation(value = "修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult<Integer> updateStatus(@PathVariable Long id, Integer status) {
        int count = roleService.updateStatus(id, status);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }


    @ApiOperation(value = "给角色分配菜单")
    @PostMapping("/allocMenu")
    public CommonResult<Integer> allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        return CommonResult.success(count);
    }

    @ApiOperation(value = "给角色分配资源")
    @PostMapping("/allocResource")
    public CommonResult<Integer> allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return CommonResult.success(count);
    }

    @ApiOperation(value = "查询角色可获取菜单")
    @GetMapping("/listMenu/{id}")
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long id) {
        List<UmsMenu> menuList = roleService.listMenu(id);
        return CommonResult.success(menuList);
    }

    @ApiOperation(value = "查询角色可使用资源")
    @GetMapping("/listResource/{id}")
    public CommonResult<List<UmsResource>> listResource(@PathVariable Long id) {
        List<UmsResource> resourceList = roleService.listResource(id);
        if (resourceList == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(resourceList);
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping("/update/{id}")
    private CommonResult<Integer> updateRole(@PathVariable Long id,
                                             @RequestBody UmsRole role) {
        int count = roleService.updateRole(id, role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "批量删除角色")
    @PostMapping("/delete")
    public CommonResult<Integer> deleteRoles(@RequestParam("ids") List<Long> ids) {
        int count = roleService.deleteRoles(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/create")
    public CommonResult<Integer> createRole(@RequestBody UmsRole role) {
        int count = roleService.createRole(role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/listAll")
    public CommonResult<List<UmsRole>> listAll() {
        List<UmsRole> roleList = roleService.listAll();
        return CommonResult.success(roleList);
    }

    @ApiOperation(value = "根据角色名称分页查询角色列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsRole> roleList = roleService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(roleList));
    }
}
