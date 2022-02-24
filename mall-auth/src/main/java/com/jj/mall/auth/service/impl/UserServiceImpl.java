package com.jj.mall.auth.service.impl;

import com.jj.mall.auth.constant.MessageConstant;
import com.jj.mall.auth.domain.SecurityUser;
import com.jj.mall.auth.service.UserAdminService;
import com.jj.mall.common.constant.AuthConstant;
import com.jj.mall.common.domain.UserDto;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 用户信息管理用户类
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UserAdminService userAdminService;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserDto userDto = null;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            userDto = userAdminService.loadByUserName(s);
        }
        if(userDto == null){
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if(!securityUser.isEnabled()){
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        }else if(!securityUser.isAccountNonExpired()){
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        }else if(!securityUser.isAccountNonLocked()){
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        }else if(!securityUser.isCredentialsNonExpired()){
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;

    }
}
