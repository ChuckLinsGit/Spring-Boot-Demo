package com.example.demo.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Future;

@Component
public class Schedule {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
//    @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
//    @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
//    @Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    public static Random random =new Random();

//    @Async
    @Async("taskExecutor")//可以指定线程池
    public Future<String> doTaskOne() throws Exception {
        System.out.println(Thread.currentThread().getName()+":开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成！");
    }

//    @Async
        @Async("taskExecutor")//可以指定线程池
    public Future<String> doTaskTwo() throws Exception {
        System.out.println(Thread.currentThread().getName()+":开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成！");
    }

//    @Async
        @Async("taskExecutor")//可以指定线程池
    public Future<String> doTaskThree() throws Exception {
        System.out.println(Thread.currentThread().getName()+":开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成！");
    }
}
