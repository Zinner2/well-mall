package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.PmsProduct;
import com.jj.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台商品管理Controller
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Api(tags = "pmsProductController",value = "后台商品管理")
@RequestMapping("/product")
@RestController
public class PmsProductController {

    @Resource
    private PmsProductService productService;

    @ApiOperation(value = "分页获取商品列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> list(@RequestParam Integer pageNum,
                                                     @RequestParam Integer pageSize){
        List<PmsProduct> list = productService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
