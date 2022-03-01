package com.jj.mall.admin.controller;

import com.jj.mall.admin.dto.UmsAdminLoginParam;
import com.jj.mall.admin.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.domain.UserDto;
import com.jj.mall.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台管理控制器
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Api(tags = "userAdminController",value = "后台管理")
@RestController
@RequestMapping("/admin")
public class UserAdminController {

    @Resource
    private UserAdminService userAdminService;

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public CommonResult info(){
        UmsAdmin admin = userAdminService.getCurrentAdmin();
        Map<String, Object> data = new HashMap<>();
        data.put("icon",admin.getIcon());
        return CommonResult.success(data);
    }
    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam loginParam){
        return userAdminService.login(loginParam.getUsername(), loginParam.getPassword());
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = userAdminService.loadUserByUsername(username);
        return userDTO;
    }

}
