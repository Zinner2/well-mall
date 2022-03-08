package com.jj.mall.controller;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsResourceCategory;
import com.jj.mall.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台资源目录管理Controller
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
@Api(tags = "resourceCategoryController", value = "后台资源目录")
@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Resource
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation(value = "删除资源目录")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> deleteCategory(@PathVariable Long id){
       int count = resourceCategoryService.deleteCategory(id);
       if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation(value = "修改资源目录")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> updateCategory(@PathVariable Long id,
                                                @RequestBody UmsResourceCategory resourceCategory){
        int count = resourceCategoryService.updateCategory(id, resourceCategory);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "创建资源目录")
    @PostMapping("/create")
    public CommonResult<Integer> createCategory(@RequestBody UmsResourceCategory resourceCategory){
        int count = resourceCategoryService.createCategory(resourceCategory);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "获取所有资源目录")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAllCategories() {
        List<UmsResourceCategory> resourceCategoryList = resourceCategoryService.listAllCategories();
        return CommonResult.success(resourceCategoryList);
    }
}
