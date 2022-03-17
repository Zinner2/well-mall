package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.dto.PmsBrandParam;
import com.jj.mall.model.PmsBrand;
import com.jj.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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



    @ApiOperation(value = "批量更新显示状态")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        int count = pmsBrandService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @PostMapping("/update/factoryStatus")
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        int count = pmsBrandService.updateFactoryStatus(ids, factoryStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "删除品牌")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
    @ApiOperation(value = "添加品牌")
    @PostMapping("/create")
    public CommonResult create(@Validated @RequestBody PmsBrandParam pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "修改品牌信息")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> update(
            @PathVariable Long id,
            @RequestBody PmsBrandParam brandParam){
        int result = pmsBrandService.update(id, brandParam);
        if(result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "分页获取品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> list(@RequestParam Integer pageNum,
                                                   @RequestParam Integer pageSize){
        List<PmsBrand> list = pmsBrandService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "根据id获取品牌信息")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> getBrandId(@PathVariable Long id){
        PmsBrand brand = pmsBrandService.getBrandId(id);
        return CommonResult.success(brand);
    }
}
