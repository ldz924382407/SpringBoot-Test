package com.read.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 用于测试读取配置文件信息
 * @Author 211145187
 * @Date 2022/7/20 15:51
 **/
@ConfigurationProperties(prefix = "demo")//这个注解用于找到配置文件里内容的前缀    注意：@ConfigurationProperties无法加载自定义配置问价内容，必须和@PropertySource配合使用才能获取
@Component  //生效的两种方式：方式1：配置@Component，方式2：启动类添加@EnableConfigurationProperties(ReadProperties.class)
@PropertySource(value = {"classpath:my.properties"})
@Data
public class ReadProperties3 {
    private String name;
}
