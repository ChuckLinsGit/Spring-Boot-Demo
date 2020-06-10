package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置队列、交换器、路由等高级信息
 */
@Configuration
public class Configurator {

    @Bean
    public Queue helloQueue(){
       return new Queue("hello-queue");
    }
}
