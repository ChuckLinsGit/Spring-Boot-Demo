package com.example.demo.jpa.mutildatasourceconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * 第一数据源
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.example.demo.jpa.mutildatasourceconfig.domain.primary"})
 public  class PrimaryConfig{

        @Autowired
        @Qualifier("primaryDataSource")
        private DataSource primaryDataSource;

        @Autowired
        private JpaProperties jpaProperties;

        @Autowired
        HibernateProperties hibernateProperties;

        /**
         * 配置entityManagerFactory以指定Entity实体和数据源
         * @param builder
         * @return
         */
        @Primary
        @Bean("entityManagerPrimary")
        public EntityManager entityManagerPrimary(EntityManagerFactoryBuilder builder){
            return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
        }

        @Primary
        @Bean("entityManagerFactoryPrimary")
        public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder){
            return builder
                        .dataSource(primaryDataSource)
                        .properties(getVendorProperties(primaryDataSource))
                        .packages("com.example.demo.jpa.mutildatasourceconfig.domain.primary")
                        .persistenceUnit("primaryPersistenceUnit")
                        .build();
        }

        private Map<String,Object> getVendorProperties(DataSource dataSource){
            return hibernateProperties.determineHibernateProperties(
                    jpaProperties.getProperties(), new HibernateSettings());
        }

// getHibernateProperties()方法在Springboot2.0中已被删减，因此需要通过注入HibernateProperties来解决
// 思路来源：查看官方在github项目issue栏目
//        private Map<String, String> getVendorProperties(DataSource dataSource) {
//            return jpaProperties.getHibernateProperties(dataSource);
//        }

        /**
         * 配置transactionManager以完成事务设定
         * @param builder
         * @return
         */
        @Primary
        @Bean(name="transactionManagerPrimary")
        public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder){
            return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
        }
}
