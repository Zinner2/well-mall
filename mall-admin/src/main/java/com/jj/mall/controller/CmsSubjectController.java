package com.jj.mall.controller;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.CmsSubject;
import com.jj.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品主题Controller
 * @author 任人子
 * @date 2022/3/11  - {TIME}
 */
@Api(tags = "cmsSubjectController")
@RestController
@RequestMapping("/subject")
public class CmsSubjectController {

    @Resource
    private CmsSubjectService subjectService;

    @ApiOperation(value = "获取所有商品主题")
    @GetMapping("/listAll")
    public CommonResult<List<CmsSubject>> listAll(){
        List<CmsSubject> list = subjectService.listAll();
        return CommonResult.success(list);

    }
}
