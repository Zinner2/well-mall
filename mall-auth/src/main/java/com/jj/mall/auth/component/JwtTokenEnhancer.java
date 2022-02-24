package com.jj.mall.auth.component;

import com.jj.mall.auth.domain.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken 增强器
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        SecurityUser  securityUser = (SecurityUser) oAuth2Authentication.getPrincipal();
        Map<String, Object> information = new HashMap<>(2);
        information.put("id", securityUser.getId());
        information.put("client_id", securityUser.getClientId());
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(information);
        return oAuth2AccessToken;
    }

}
