package com.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot使用flayway自动执行数据库升级脚本
 * @Author 211145187
 * @Date 2022/9/20 14:33
 **/
@SpringBootApplication
public class FlaywayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlaywayApplication.class, args);
    }
}
