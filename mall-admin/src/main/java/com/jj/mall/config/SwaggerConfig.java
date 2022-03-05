package com.jj.mall.config;

import com.jj.mall.common.config.BaseSwaggerConfig;
import com.jj.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.jj.mall.controller")
                .title("mall后台系统")
                .Description("mall后台相关接口文档")
                .contactName("任人子")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
