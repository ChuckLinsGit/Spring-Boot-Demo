//package com.example.demo.JDBC.mutildatasourceconfig;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * JDBC多数据源配置类
// */
//@Configuration
//public class DataSourceConfiguration {
//
//    @Bean("primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSource primaryDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSource secondaryDataSource(){
//        return  DataSourceBuilder.create().build();
//    }
//}
