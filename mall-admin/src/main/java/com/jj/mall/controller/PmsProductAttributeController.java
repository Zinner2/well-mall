package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.PmsProductAttribute;
import com.jj.mall.model.PmsProductCategory;
import com.jj.mall.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "分页获取商品参数列表")
    @GetMapping("/category/list")
    public CommonResult<CommonPage<PmsProductAttribute>> category(@RequestParam Integer pageNum,
                                                                  @RequestParam Integer pageSize){
        List<PmsProductAttribute> list = pmsProductAttributeService.category(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
