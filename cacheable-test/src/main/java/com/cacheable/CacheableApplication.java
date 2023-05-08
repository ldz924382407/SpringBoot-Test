package com.cacheable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 测试SpringBoot本地缓存技术
 * @Author 211145187
 * @Date 2022/9/20 14:33
 **/
@SpringBootApplication
@EnableCaching
public class CacheableApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheableApplication.class, args);
    }
}
