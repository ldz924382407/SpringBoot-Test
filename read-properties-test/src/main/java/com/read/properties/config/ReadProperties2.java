package com.read.properties.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 用于测试读取配置文件信息
 * @Author 211145187
 * @Date 2022/7/20 15:47
 **/
@PropertySource(value = {"classpath:application.properties"})
@Component
@Data
public class ReadProperties2 {
    @Value("${server.port}")
    private Integer port;
}
