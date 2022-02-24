package com.jj.mall.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 获取RAS公匙接口
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@RestController
@RequestMapping("rsa")
public class KeyPairController {
    @Resource
    private KeyPair keyPair;
    @GetMapping("/publicKey")
    public Map<String, Object> getKey(){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey rAS = new RSAKey.Builder(publicKey).build();
        return new JWKSet(rAS).toJSONObject();
    }
}
