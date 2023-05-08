package com.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用swagger,用于生成、描述、调用和可视化 RESTful 风格的 Web 服务
 * @Author 211145187
 * @Date 2022/9/20 14:33
 **/
@SpringBootApplication
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
