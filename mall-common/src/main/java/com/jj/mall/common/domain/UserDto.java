package com.jj.mall.common.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;

/**
 * 登录用户信息
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor()
public class UserDto {
    private long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;

}
