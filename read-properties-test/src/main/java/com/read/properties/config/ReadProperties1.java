package com.read.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用于测试读取配置文件信息
 * 1）注解@ConfigurationProperties中的prefix用于设置前缀
 * 2）下方的属性名称必须和要获取的配置信息名称一致，比如必须叫port，否则获取值为null
 */
@ConfigurationProperties(prefix = "server")//这个注解用于找到配置文件里内容的前缀
@Component  //生效的两种方式：方式1：配置@Component，方式2：启动类添加@EnableConfigurationProperties(ReadProperties.class)
@Data
public class ReadProperties1 {
    private Integer port;
}
