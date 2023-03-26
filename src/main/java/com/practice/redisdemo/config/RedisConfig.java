package com.practice.redisdemo.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create(RedisURI.builder()
                .withHost("127.0.0.1")
                .withPort(6379)
                .build());

    }

    @Bean
    public StatefulRedisConnection<String, String> statefulRedisConnection(RedisClient redisClient) {
        return redisClient.connect();
    }

    @Bean
    public RedisAsyncCommands<String, String> asyncCommands(StatefulRedisConnection<String, String> redisConnection) {
        return redisConnection.async();
    }
}
