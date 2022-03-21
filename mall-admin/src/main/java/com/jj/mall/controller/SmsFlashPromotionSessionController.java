package com.jj.mall.controller;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.SmsFlashPromotionSessionDetail;
import com.jj.mall.model.SmsFlashPromotionSession;
import com.jj.mall.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 限时购场次管理Controller
 * @author 任人子
 * @date 2022/3/21  - {TIME}
 */
@Api(tags = "smsFlashPromotionSessionController")
@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Resource
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("获取所有可选场次")
    @GetMapping("/selectList")
    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectAll(Long flashPromotionId){
        List<SmsFlashPromotionSessionDetail> list = flashPromotionSessionService.selectAll(flashPromotionId);
        return CommonResult.success(list);
    }
    @ApiOperation("修改场次状态")
    @PostMapping("/update/status/{id}")
    public CommonResult<Integer> updateStatus(@PathVariable Long id, Integer status){
       int count = flashPromotionSessionService.updateStatus(id, status);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }

    @ApiOperation("删除场次")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id){
        int count = flashPromotionSessionService.delete(id);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }
    @ApiOperation("修改场次")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession flashPromotionSession){
        int count = flashPromotionSessionService.update(id, flashPromotionSession);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }

    @ApiOperation("添加场次")
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody SmsFlashPromotionSession flashPromotionSession){
        int count = flashPromotionSessionService.create(flashPromotionSession);
        if(count < 0){
            return CommonResult.failed();
        }
        return CommonResult.success(count);
    }

    @ApiOperation("获取所有场次")
    @GetMapping("/list")
    public CommonResult<List<SmsFlashPromotionSession>> getList(){
        List<SmsFlashPromotionSession> res = flashPromotionSessionService.getList();
        return CommonResult.success(res);
    }
}
