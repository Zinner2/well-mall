package com.jj.mall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 管理员登录参数
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */
@Data
@EqualsAndHashCode
public class UmsAdminLoginParam {
    @ApiModelProperty(value = "登录账号")
    @NotNull
    private String username;
    @ApiModelProperty(value = "登录密码")
    @NotNull
    private String password;
}
