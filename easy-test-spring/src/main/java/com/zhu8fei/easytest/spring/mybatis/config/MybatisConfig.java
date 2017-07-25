package com.zhu8fei.easytest.spring.mybatis.config;

import com.zhu8fei.easytest.core.data.DataProcessor;
import com.zhu8fei.easytest.core.data.SimpleJsonProcessorImpl;
import com.zhu8fei.easytest.core.exception.EasyTestException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by zhu8fei on 2017/5/4.
 */
@Profile("easy-test")
@MapperScan("com.zhu8fei.easytest.core.commons.mybatis.mapper")
@ComponentScan(basePackages = "com.zhu8fei")
@Configuration
public class MybatisConfig implements TransactionManagementConfigurer {
    private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
    @Resource
    private DataSource dataSource;

    /**
     * 工厂方法 为 mock提供切入口
     */
    public SqlSessionFactoryBean getSqlSessionFactoryBeanInstance() {
        return new SqlSessionFactoryBean();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws EasyTestException {
        // 原写法
        //   SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 这样做. 没办法mock到. 于是使用工厂方法
        SqlSessionFactoryBean bean = getSqlSessionFactoryBeanInstance();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.zhu8fei.**.mybatis.model");

        try {
            return bean.getObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EasyTestException(e.getMessage(), e);
        }
    }

    @Bean("simpleJsonProcessorIpml")
    public DataProcessor simpleJsonProcessorIpml(){
        return new SimpleJsonProcessorImpl();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
