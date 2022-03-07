package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsRole;
import com.jj.mall.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户角色管理
 * @author 任人子
 * @date 2022/3/6  - {TIME}
 */
@RestController()
@RequestMapping("/role")
public class UmsRoleController {

    @Resource
    private UmsRoleService roleService;

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
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
       List<UmsRole> roleList = roleService.list(keyword, pageNum, pageSize);
       return CommonResult.success(CommonPage.restPage(roleList));
    }
}
