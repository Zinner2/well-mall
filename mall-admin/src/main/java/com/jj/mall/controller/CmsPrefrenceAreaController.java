package com.jj.mall.controller;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.CmsPrefrenceArea;
import com.jj.mall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品优选Controller
 * @author 任人子
 * @date 2022/3/11  - {TIME}
 */
@Api(tags = "cmsPrefrenceAreaController",value = "商品优选")
@RequestMapping("/prefrenceArea")
@RestController
public class CmsPrefrenceAreaController {
    @Resource
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation(value = "获取所有优选商品")
    @GetMapping("/listAll")
    public CommonResult<List<CmsPrefrenceArea>> listAll(){
        List<CmsPrefrenceArea> list = prefrenceAreaService.listAll();
        return CommonResult.success(list);
    }
}
