package com.jj.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 任人子
 * @date 2022/2/25  - {TIME}
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MallGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGateWayApplication.class, args);
    }
}
