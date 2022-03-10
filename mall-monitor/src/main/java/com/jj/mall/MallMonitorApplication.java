package com.jj.mall;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 任人子
 * @date 2022/3/10  - {TIME}
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class MallMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallMonitorApplication.class, args);
    }
}
