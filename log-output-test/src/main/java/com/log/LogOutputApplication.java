package com.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot项目使用log4j2，日志配置文件log4j2.xml的使用说明
 * @Author 211145187
 * @Date 2022/9/20 14:33
 **/
@SpringBootApplication
public class LogOutputApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogOutputApplication.class, args);
    }
}
