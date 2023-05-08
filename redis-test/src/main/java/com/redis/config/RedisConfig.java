package com.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    /**
     *
     * @param factory factory
     * @author liudz
     * @date 2021/1/6
     * @return 执行结果
     **/
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //key的序列化采用String类型的
        template.setKeySerializer(RedisSerializer.string());
        //value的序列化采用jackson类型
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash的key的序列化也采用String类型
        template.setHashKeySerializer(RedisSerializer.string());
        //value的序列化采用jackson类型
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}

