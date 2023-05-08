package com.log.controller;

import com.log.bean.User;
import com.log.service.LogOutputService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试日志框架输出打印
 * @Author 211145187
 * @Date 2022/9/19 16:31
 **/
@RestController
@RequestMapping("/level")
public class LogOutputController {
    private static Log log = LogFactory.getLog(LogOutputController.class);

    @Autowired
    private LogOutputService logOutputService;

    //测试SLF4J+Log4j2日志框架
    @RequestMapping("")
    public void consoleLevel() {
        User user = new User();
        user.setName("cat");
        user.setPassword("123");
        log.debug("===这里是Controller层=======普通打印，这是debug级别");
        log.info("===这里是Controller层=======普通打印，这是info级别");
        log.warn("===这里是Controller层=======普通打印，这是warn级别");
        log.error("===这里是Controller层=======普通打印，这是error级别");
        logOutputService.getUserInfo();
    }
}
