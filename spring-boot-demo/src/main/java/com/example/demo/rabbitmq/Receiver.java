package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello-queue")     //启动监听器
public class Receiver {

    //消息处理
    @RabbitHandler
    public void process(Queue queue,String hello){
        System.out.println("Receiver "+queue.getClass());
        System.out.println("Receiver "+hello);
    }
}
