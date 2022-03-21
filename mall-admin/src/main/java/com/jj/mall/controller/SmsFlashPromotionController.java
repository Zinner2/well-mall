package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.SmsFlashPromotion;
import com.jj.mall.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限时购活动管理Controller
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
@Api(tags = "smsFlashPromotionController", value = "限时购活动管理")
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Resource
    private SmsFlashPromotionService flashPromotionService;

    @ApiOperation("修改上下线状态")
    @PostMapping("/update/status/{id}")
    public CommonResult<Integer> updateStatusFlashPromotion(@PathVariable Long id, Integer status){
        int count = flashPromotionService.updateStatusFlashPromotion(id, status);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }
    @ApiOperation("删除限时活动")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> deleteFlash(@PathVariable Long id){
        int result = flashPromotionService.deleteFlash(id);
        if(result < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(result);
    }

    @ApiOperation("修改限时活动")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> updateFlash(@PathVariable Long id, @RequestBody SmsFlashPromotion promotion){
        int count = flashPromotionService.updateFlash(id, promotion);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }
    @ApiOperation("创建限时活动")
    @PostMapping("/create")
    public CommonResult<Integer> createFlash(@RequestBody SmsFlashPromotion promotion){
        int count = flashPromotionService.createFlash(promotion);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }

    @ApiOperation("根据活动名称分页查询")
    @GetMapping("/list")
    public Object getItem(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(flashPromotionList));
    }

}
