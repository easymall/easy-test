package com.zhu8fei.easytest.core.commons.mybatis.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * 不使用spring boot的时候. 需要指定DataSource
 *
 * Created by zhu8fei on 2017/5/6.
 */
@Profile("easy-test")
@Configuration
public class DataResourceConfig {
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&aut");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setInitialSize(3);
        dataSource.setMinIdle(3);
        dataSource.setMaxIdle(5);
        dataSource.setMaxTotal(10);
        return dataSource;
    }
}
