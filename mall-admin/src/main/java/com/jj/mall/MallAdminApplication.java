package com.jj.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * admin 主启动类
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class,args);
    }
}
