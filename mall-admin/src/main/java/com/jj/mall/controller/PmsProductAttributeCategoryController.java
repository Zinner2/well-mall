package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.PmsProductAttributeCategoryItem;
import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductAttributeCategory;
import com.jj.mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品属性分类Controller
 * @author 任人子
 * @date 2022/3/12  - {TIME}
 */
@Api(tags = "pmsProductAttributeCategoryController",value = "商品属性分类管理")
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Resource
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @PostMapping("/create")
    public CommonResult create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryList));
    }
    @ApiOperation(value = "获取所有商品属性分类以及其下属性")
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr(){
        List<PmsProductAttributeCategoryItem> list = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(list);
    }
}
