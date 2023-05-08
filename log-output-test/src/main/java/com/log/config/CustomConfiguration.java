package com.log.config;

import com.log.bean.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 211145187
 * @Date 2022/10/28 10:31
 **/
@Configuration
public class CustomConfiguration {
    private static Log log = LogFactory.getLog(CustomConfiguration.class);
    @Bean
    public User userBean() {
        log.info("userBean loading finash!");
        User user = new User("tom", "123");
        log.debug("==========level-debug-user:{}"+ user);
        log.info("==========level-info-user:{}"+ user);
        log.warn("==========level-warn-user:{}"+ user);
        log.error("==========level-error-user:{}"+ user);
        return user;
    }
}
