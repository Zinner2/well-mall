package com.jj.mall.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jj.mall.auth.constant.MessageConstant;
import com.jj.mall.auth.domain.SecurityUser;
import com.jj.mall.auth.service.UmsAdminService;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户管理业务类
 * @author 张俊杰
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UmsAdminService adminService;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String clientId = request.getParameter("client_id");
        UserDto userDto = null;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            // 调用微服务查询用户信息
            userDto = adminService.loadUserByUsername(username);
        }
        if (userDto==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

}
