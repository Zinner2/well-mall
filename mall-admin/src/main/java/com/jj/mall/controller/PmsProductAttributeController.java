package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.PmsProductAttributeParam;
import com.jj.mall.dto.ProductAttrInfo;
import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductCategory;
import com.jj.mall.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台商品参数Controller
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Api(tags = "pmsProductAttributeController", value = "商品参数管理")
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Resource
    private PmsProductAttributeService pmsProductAttributeService;

    @ApiOperation("修改商品属性信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = pmsProductAttributeService.update(id, productAttributeParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询单个商品属性")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductAttribute> getItem(@PathVariable Long id) {
        PmsProductAttribute productAttribute = pmsProductAttributeService.getItem(id);
        return CommonResult.success(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = pmsProductAttributeService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("添加商品属性信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody PmsProductAttributeParam productAttributeParam) {
        int count = pmsProductAttributeService.create(productAttributeParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    @ResponseBody
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = pmsProductAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoList);
    }
    @ApiOperation(value = "分页获取商品属性或参数列表 ? type == 0 ? 属性 : 参数")
    @GetMapping("/list/{id}")
    public CommonResult<CommonPage<PmsProductAttribute>> list(@PathVariable Long id,
                             @RequestParam Integer type,
                             @RequestParam Integer pageNum,
                             @RequestParam Integer pageSize){
        List<PmsProductAttribute> list = pmsProductAttributeService.list(id, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


}
