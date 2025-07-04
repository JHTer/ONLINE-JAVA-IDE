package org.olexec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ExecutorConfig {
    @Value("${executor.core-pool:5}")
    private int core;
    @Value("${executor.max-pool:5}")
    private int max;
    @Value("${executor.queue-capacity:5}")
    private int queue;

    @Bean("userCodeExecutor")
    public org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor userCodeExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(core);
        exec.setMaxPoolSize(max);
        exec.setQueueCapacity(queue);
        exec.setThreadNamePrefix("user-code-");
        exec.initialize();
        return exec;
    }

    @Bean
    public java.util.concurrent.ExecutorService userCodeExecutorService(org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor exec) {
        return exec.getThreadPoolExecutor();
    }
} 