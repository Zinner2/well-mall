package com.jj.mall.admin.comtroller;

import com.jj.mall.admin.dto.UmsAdminLoginParam;
import com.jj.mall.admin.service.UserAdminService;
import com.jj.mall.common.api.CommonResult;
import com.jj.mall.common.domain.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台管理控制器
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Api(tags = "userAdminController")
@RestController
@RequestMapping("admin")
public class UserAdminController {

    @Resource
    private UserAdminService userAdminService;

    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam loginParam){
        return userAdminService.login(loginParam.getUsername(), loginParam.getPassword());
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @GetMapping("/loadByUserName")
    @ResponseBody
    public UserDto loadByUserName(String username) {
        return userAdminService.loadUserByUsername(username);
    }


}
