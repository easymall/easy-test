package com.zhu8fei.easytest.spring.mybatis.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 不使用spring boot的时候. 需要指定DataSource
 * <p>
 * Created by zhu8fei on 2017/5/6.
 */
@Configuration
public class DataResourceConfig {
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        String connectURI = System.getProperty("easy.test.base.db.url");
        if (StringUtils.isNotEmpty(connectURI)) {
            connectURI = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&aut";
        }
        basicDataSource.setUrl(connectURI);
        String username = System.getProperty("easy.test.base.db.username");
        if (StringUtils.isNotEmpty(username)) {
            username = "root";
        }
        basicDataSource.setUsername(username);
        String password = System.getProperty("easy.test.base.db.password");
        if (StringUtils.isNotEmpty(password)) {
            password = "";
        }
        basicDataSource.setPassword(password);
        String driverClass = System.getProperty("easy.test.base.db.driver");
        if (StringUtils.isNotEmpty(driverClass)) {
            driverClass = "com.mysql.jdbc.Driver";
        }
        basicDataSource.setDriverClassName(driverClass);
        int initialSize = 5;
        String initialSizeStr = System.getProperty("easy.test.base.db.initSize");
        if (StringUtils.isNotEmpty(initialSizeStr)) {
            initialSize = Integer.parseInt(initialSizeStr);
        }
        basicDataSource.setInitialSize(initialSize); // 初始的连接数；
        int maxTotal = 10;
        String maxTotalStr = System.getProperty("easy.test.base.db.maxTotal");
        if (StringUtils.isNotEmpty(maxTotalStr)) {
            maxTotal = Integer.parseInt(maxTotalStr);
        }
        basicDataSource.setMaxTotal(maxTotal);
        int maxIdle = 5;
        String maxIdleStr = System.getProperty("easy.test.base.db.maxIdle");
        if (StringUtils.isNotEmpty(maxIdleStr)) {
            maxIdle = Integer.parseInt(maxIdleStr);
        }
        basicDataSource.setMaxIdle(maxIdle);
        int maxWaitMillis = 5;
        String maxWaitMillisStr = System.getProperty("easy.test.base.db.maxWaitMillis");
        if (StringUtils.isNotEmpty(maxWaitMillisStr)) {
            maxWaitMillis = Integer.parseInt(maxWaitMillisStr);
        }
        basicDataSource.setMaxWaitMillis(maxWaitMillis);
        int minIdle = 1;
        String minIdleStr = System.getProperty("easy.test.base.db.minIdle");
        if (StringUtils.isNotEmpty(minIdleStr)) {
            minIdle = Integer.parseInt(minIdleStr);
        }
        basicDataSource.setMinIdle(minIdle);
        return basicDataSource;
    }
}
