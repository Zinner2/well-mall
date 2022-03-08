package com.jj.mall.controller;

import com.jj.mall.common.api.CommonPage;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.UmsAdmin;
import com.jj.mall.model.UmsResource;
import com.jj.mall.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台资源管理Controller
 *
 * @author 任人子
 * @date 2022/3/7  - {TIME}
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "umsResourceController", value = "后台资源管理")
public class UmsResourceController {

    @Resource
    private UmsResourceService resourceService;


    @ApiOperation(value = "查询所有后台资源")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResource>> listAll(){
        List<UmsResource> resourceList = resourceService.listAll();
        if(resourceService == null){
            return CommonResult.failed();
        }
        return CommonResult.success(resourceList);
    }
    @ApiOperation(value = "修改后台资源")
    @PostMapping("/update/{id}")
    public CommonResult<Integer> updateResource(@PathVariable Long id,
                                                @RequestBody UmsResource resource) {
        int count = resourceService.updateResource(id, resource);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除后台资源")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> deleteResource(@PathVariable Long id) {
        int count = resourceService.deleteResource(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "添加后台资源")
    @PostMapping("/create")
    public CommonResult<Integer> createResource(@RequestBody UmsResource resource) {
        int count = resourceService.createResource(resource);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "分页模糊查询资源列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                                      @RequestParam(value = "nameKeyword", required = false) String nameKeyword,
                                                      @RequestParam(value = "urlKeyword", required = false) String urlKeyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsResource> adminList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(adminList));
    }
}
