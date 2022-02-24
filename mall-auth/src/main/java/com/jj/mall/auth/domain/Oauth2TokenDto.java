package com.jj.mall.auth.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2获取Token返回信息封装
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
@Data
@Builder
@EqualsAndHashCode()
public class Oauth2TokenDto {

    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷新令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHeader;
    @ApiModelProperty(value = "有效时间")
    private int expiresIn;
}
