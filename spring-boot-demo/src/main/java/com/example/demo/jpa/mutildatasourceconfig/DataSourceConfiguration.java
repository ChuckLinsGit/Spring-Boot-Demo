package com.example.demo.jpa.mutildatasourceconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * JPA多数据源配置类
 * ConfigurationProperties中加入的prefix报错的原因是：JDBC多数据源配置中也存在DataSourceConfiguration，但一个项目中每个数据源都是唯一的，因此报错
 * 只要注释一个数据源，另一个数据源就能用了。
 *
 */
@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean("primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){
        return  DataSourceBuilder.create().build();
    }
}
