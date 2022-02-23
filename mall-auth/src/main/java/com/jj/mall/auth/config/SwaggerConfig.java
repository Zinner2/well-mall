package com.jj.mall.auth.config;

import com.jj.mall.common.config.BaseSwaggerConfig;
import com.jj.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Api 文档相关配置
 * @author 任人子
 * @date 2022/2/23  - {TIME}
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                    .apiBasePackage("com.jj.mall.auth.controller")
                    .Description("well-mall统一认证中心接口文档")
                    .contactName("renrenzi")
                    .version("1.0")
                    .title("well-mall统一认证中心")
                    .enableSecurity(true)
                    .build();
    }
}
