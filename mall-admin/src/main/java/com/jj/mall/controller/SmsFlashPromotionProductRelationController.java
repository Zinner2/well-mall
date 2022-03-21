package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.SmsFlashPromotionProduct;
import com.jj.mall.model.SmsFlashPromotion;
import com.jj.mall.model.SmsFlashPromotionProductRelation;
import com.jj.mall.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *  限时购和商品关系管理Controller
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
@Api(tags = "SmsFlashPromotionProductRelationController")
@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Resource
    private SmsFlashPromotionProductRelationService flashPromotionProductRelationService;

    @ApiOperation("删除关联信息")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id){
        int count = flashPromotionProductRelationService.delete(id);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }
    @ApiOperation("修改关联商品信息")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation productRelation){
        int count = flashPromotionProductRelationService.update(id, productRelation);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }

    @ApiOperation("限时购添加商品")
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody List<SmsFlashPromotionProductRelation> relationList){
        int count = flashPromotionProductRelationService.create(relationList);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }

    @ApiOperation("分页获取限购商品列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotionProduct>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsFlashPromotionProduct> flashPromotionProductList = flashPromotionProductRelationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(flashPromotionProductList));
    }
}
