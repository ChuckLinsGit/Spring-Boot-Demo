package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 用来启动整个应用的主类
 */
@SpringBootApplication
//@EnableCaching//允许缓存
//在Spring Boot中通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），Spring Boot根据下面的顺序去侦测缓存提供者：
//
//        Generic
//        JCache (JSR-107)
//        EhCache 2.x
//        Hazelcast
//        Infinispan
//        Redis
//        Guava
//        Simple
//除了按顺序侦测外，我们也可以通过配置属性spring.cache.type来强制指定。

//@EnableScheduling//允许周期调用
//@EnableAsync//允许异步调用,如果存在自定义线程池，可以在线程池上使用该注释，此处就可以不使用
public class DemoApplication {
    @Bean
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
