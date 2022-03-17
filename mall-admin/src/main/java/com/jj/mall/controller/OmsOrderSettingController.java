package com.jj.mall.controller;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.OmsOrderSetting;
import com.jj.mall.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单设置
 * @author 任人子 Controller
 * @date 2022/3/17  - {TIME}
 */
@Api(tags = "omsOrderSettingController")
@RestController
@RequestMapping("orderSetting")
public class OmsOrderSettingController {

    @Resource
    private OmsOrderSettingService orderSettingService;

    @ApiOperation("获取订单设置")
    @GetMapping("/{id}")
    public CommonResult<OmsOrderSetting> getItem(@PathVariable Long id) {
        OmsOrderSetting orderSetting = orderSettingService.getItem(id);
        return CommonResult.success(orderSetting);
    }
    @ApiOperation("修改指定订单设置")
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody OmsOrderSetting orderSetting) {
        int count = orderSettingService.update(id,orderSetting);
        if(count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
