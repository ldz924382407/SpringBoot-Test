package com.log.service.impl;

import com.log.service.LogOutputService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * @Author 211145187
 * @Date 2022/10/31 13:26
 **/
@Service
public class LogOutputServiceImpl implements LogOutputService {
    private static Log log = LogFactory.getLog(LogOutputServiceImpl.class);

    @Override
    public void getUserInfo() {
        log.debug("===这里是Service层=======普通打印，这是debug级别");
        log.info("===这里是Service层=======普通打印，这是info级别");
        log.warn("===这里是Service层=======普通打印，这是warn级别");
        log.error("===这里是Service层=======普通打印，这是error级别");
    }
}
