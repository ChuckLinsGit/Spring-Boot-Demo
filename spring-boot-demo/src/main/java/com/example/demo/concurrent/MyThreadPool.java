package com.example.demo.concurrent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
//@EnableAsync
@Configuration
public class MyThreadPool {
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);//保证异步任务在依赖任务销毁前销毁
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
