package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsMenu;
import com.jj.mall.service.UmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台菜单Controller
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "umsMenuController",value = "后台菜单管理")
public class UmsMenuController {

    @Resource
    private UmsMenuService menuService;



    @ApiOperation(value = "修改后台菜单")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> updateMenu(@PathVariable Long id,
                                            @RequestBody UmsMenu umsMenu){
        int count = menuService.updateMenu(id, umsMenu);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除后台菜单")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> deleteId(@PathVariable Long id) {
        int count = menuService.delete(id);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据Id获取后台菜单")
    @GetMapping("{id}")
    public CommonResult<UmsMenu> getItem(@PathVariable Long id){
        UmsMenu umsMenu = menuService.getItem(id);
        return CommonResult.success(umsMenu);
    }

    @ApiOperation(value = "添加后台菜单")
    @PostMapping("/create")
    public CommonResult<Integer> createMenu(@RequestBody UmsMenu umsMenu){
        int count = menuService.createMenu(umsMenu);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据菜单Id分页获取菜单列表")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        List<UmsMenu> menuList = menuService.list(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(menuList));
    }


}
