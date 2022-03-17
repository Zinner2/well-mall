package com.jj.mall.controller;

import com.jj.mall.common.api.CommonResult;
import com.jj.mall.model.OmsCompanyAddress;
import com.jj.mall.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址管理 Controller
 * @author 任人子
 * @date 2022/3/17  - {TIME}
 */
@Api(tags = "omsCompanyAddressController")
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    @Resource
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @GetMapping("/list")
    public CommonResult<List<OmsCompanyAddress>> list() {
        List<OmsCompanyAddress> companyAddressList = companyAddressService.list();
        return CommonResult.success(companyAddressList);
    }
}
