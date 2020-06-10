package com.example.demo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    //通过注入AmqpTemplate接口的实例来实现消息的发送，AmqpTemplate接口定义了一套针对AMQP协议的基础操作
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String context="Sender hello rabbitmq "+new Date();
        System.out.println(context);
        //将context发送都命名为hello-queue的消息队列中
        amqpTemplate.convertAndSend("hello-queue",context);
    }
}
