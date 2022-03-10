package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.PmsProductCategoryWithChildrenItem;
import com.jj.mall.model.PmsProductCategory;
import com.jj.mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
