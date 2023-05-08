package com.read.properties.controller;

import com.read.properties.config.ReadProperties1;
import com.read.properties.config.ReadProperties2;
import com.read.properties.config.ReadProperties3;
import com.read.properties.config.ReadProperties4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 测试读取配置文件的6种方式：
 * @Author 211145187
 * @Date 2022/7/20 14:02
 **/
@RestController
public class ReadPropertiesController {
    @Autowired
    private Environment environment;
    @Value("${server.port}")
    private Integer serverPort;

    @Autowired
    private ReadProperties1 readProperties1;
    @Autowired
    private ReadProperties2 readProperties2;
    @Autowired
    private ReadProperties3 readProperties3;
    @Autowired
    private ReadProperties4 readProperties4;


    //测试方式1：通过Environment读取配置信息
    @GetMapping("/readApplicationProperties1")
    public Map<String,Object> readApplicationProperties1(){
        Map<String,Object> map = new HashMap<>();
        map.put("port",environment.getProperty("server.port"));
        System.out.println("通过Environment读取配置信息:" + environment.getProperty("server.port"));
        return  map;
    }

    //测试方式2：通过@Value注解读取配置信息
    @GetMapping("/readApplicationProperties2")
    public void readApplicationProperties2(){
        System.out.println("通过@Value注解读取配置信息:" + serverPort);
    }

    //测试方式3：通过@ConfigurationProperties注解读取配置信息
    @GetMapping("/readApplicationProperties3")
    public void readApplicationProperties3(){
        System.out.println("通过@ConfigurationProperties注解读取配置信息:" + readProperties1.getPort());
    }

    //测试方式4：通过@PropertySource+@Value注解读取配置信息
    @GetMapping("/readApplicationProperties4")
    public void readApplicationProperties4(){
        System.out.println("通过@PropertySource注解读取配置信息:" + readProperties2.getPort());
    }

    //测试方式5：通过@PropertySource+@ConfigurationProperties注解读取配置信息
    @GetMapping("/readApplicationProperties5")
    public void readApplicationProperties5(){
        System.out.println("通过@PropertySource+@ConfigurationProperties注解读取配置信息:" + readProperties3);
    }

    //测试方式6：通过Properties读取配置信息
    @GetMapping("/readApplicationProperties6")
    public void readApplicationProperties6() throws IOException {
        Resource resource = new ClassPathResource("application-prod.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String root = properties.getProperty("logging.level.root");
        System.out.println("通过xProperties读取配置信息:" + root);
    }

    //测试方式7：通过xx读取配置信息
    @GetMapping("/readApplicationProperties7")
    public void readApplicationProperties7(){
        System.out.println("通过xx读取配置信息:" + readProperties4.getRoot());
    }
}
