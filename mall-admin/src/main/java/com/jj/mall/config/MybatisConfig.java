package com.jj.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@MapperScan({"com.jj.mall.mapper","com.jj.mall.dao"})
@EnableTransactionManagement
@Configuration
public class MybatisConfig {
}