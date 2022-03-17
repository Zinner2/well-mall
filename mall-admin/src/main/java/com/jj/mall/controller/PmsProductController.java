package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.PmsProductParam;
import com.jj.mall.dto.PmsProductQueryParam;
import com.jj.mall.dto.PmsProductResult;
import com.jj.mall.model.PmsProduct;
import com.jj.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台商品管理Controller
 *
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Api(tags = "pmsProductController", value = "后台商品管理")
@RequestMapping("/product")
@RestController
public class PmsProductController {

    @Resource
    private PmsProductService productService;

    @ApiOperation("更新商品")
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @GetMapping("/updateInfo/{id}")
    @ResponseBody
    public CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Long id) {
        PmsProductResult productResult = productService.getUpdateInfo(id);
        return CommonResult.success(productResult);
    }

    @ApiOperation("批量修改删除状态")
    @PostMapping("/update/deleteStatus")
    @ResponseBody
    public CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量上下架")
    @PostMapping("/update/publishStatus")
    @ResponseBody
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量推荐商品")
    @PostMapping("/update/recommendStatus")
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量设为新品")
    @PostMapping("/update/newStatus")
    @ResponseBody
    public CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("创建商品")
    @PostMapping("/create")
    @ResponseBody
    public CommonResult create(@RequestBody PmsProductParam productParam) {
        int count = productService.create(productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "分页获取商品列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductQueryParam productQueryParam,
                                                     @RequestParam Integer pageNum,
                                                     @RequestParam Integer pageSize) {
        List<PmsProduct> list = productService.list(productQueryParam, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
