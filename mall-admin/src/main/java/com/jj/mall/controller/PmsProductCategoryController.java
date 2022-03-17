package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.PmsProductCategoryParam;
import com.jj.mall.dto.PmsProductCategoryWithChildrenItem;
import com.jj.mall.model.PmsProductCategory;
import com.jj.mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台商品目录Controller
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Api(tags = "pmsProductCategoryController", value="商品目录管理")
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {


    @Resource
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改显示状态")
    @PostMapping("/update/showStatus")
    @ResponseBody
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @Validated
                               @RequestBody PmsProductCategoryParam productCategoryParam) {
        int count = productCategoryService.update(id, productCategoryParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "添加产品分类")
    @PostMapping("/create")
    public CommonResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam){
        int count = productCategoryService.create(productCategoryParam);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据id获取商品分类信息")
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getItem(@PathVariable Long id){
        PmsProductCategory item = productCategoryService.getItem(id);
        return CommonResult.success(item);
    }

    @ApiOperation(value = "分页查询商品目录")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> list(@PathVariable Long parentId,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        List<PmsProductCategory> list = productCategoryService.list(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation(value = "查询所有一级分类和子集分类")
    @GetMapping("/list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> withChildren(){
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.withChildren();
        return CommonResult.success(list);
    }
}
