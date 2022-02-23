package com.jj.mall.auth.domain;

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
@EqualsAndHashCode(callSuper = true)
public class Oauth2TokenDto {

    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("")
    private String refreshToken;

    private String tokenHeader;

    private int  expiresIn;
