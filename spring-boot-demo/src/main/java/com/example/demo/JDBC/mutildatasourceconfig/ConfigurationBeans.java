package com.example.demo.JDBC.mutildatasourceconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 用来声明配置类Bean
 *
 * 特别注意的是本工具类必须加上@Component或其他Spring角色标签，这样才能加入Spring容器被其他类发现并使用
 */
@Component
public class ConfigurationBeans {

    @Bean("primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="secondaryJdbcTemplate")
    public  JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
