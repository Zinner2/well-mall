package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.PmsBrand;
import com.jj.mall.service.PmsBrandService;
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
 * 后台品牌管理
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@Api(tags = "pmsBrandController",value = "品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Resource
    private PmsBrandService pmsBrandService;

    @ApiOperation(value = "分页获取品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam Integer pageNum,
                                                   @RequestParam Integer pageSize){
        List<PmsBrand> list = pmsBrandService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
