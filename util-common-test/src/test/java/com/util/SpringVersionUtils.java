package com.util;

import org.junit.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

/**
 * 获取Spring、SpringBoot版本号
 * @Author 211145187
 * @Date 2022/11/12 10:42
 **/
public class SpringVersionUtils {

    @Test
    public void getSpringVersion() {
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println("Spring版本号：" + version);
        System.out.println("SpringBoot版本号：" + version1);

    }

}
