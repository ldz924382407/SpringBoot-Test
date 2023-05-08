package com.aop.custom.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试AOP通过@Around注解和自定义注解 =》 实现保存操作日志功能
 * @Author 211145187
 * @Date 2022/9/20 14:33
 **/
@SpringBootApplication
public class AopAnnotationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopAnnotationApplication.class, args);
    }
}
