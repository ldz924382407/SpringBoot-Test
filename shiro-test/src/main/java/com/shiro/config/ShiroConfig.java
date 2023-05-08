package com.shiro.config;

import com.shiro.bean.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//声明为配置类
@Configuration
public class ShiroConfig {

    //创建 realm 对象 1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //创建安全管理器 DefaultWebSecurityManager 2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建过滤器 ShiroFilterFactoryBean 3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
        添加Shiro内置过滤器，常用的有如下过滤器：
        anon： 无需认证就可以访问
        authc： 必须认证才可以访问
        user： 如果使用了记住我功能就可以直接访问
        perms:	拥有某个资源权限才可以访问
        role： 拥有某个角色权限才可以访问
        */
        Map<String,String> filterMap=new LinkedHashMap<String, String>();
        //设置过滤器，还没登录前要想访问controller的add和update必须通过登录认证才允许访问
        filterMap.put("/shiro/user/add","authc");  //这里user/*可以通配符的
        filterMap.put("/shiro/user/update","authc");
        filterMap.put("/shiro/queryList","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改到要跳转的login页面(shrio没security会帮你自动生成一个登录的，得自己写)；
        shiroFilterFactoryBean.setLoginUrl("/shiro/toLogin"); //toLogin是controller的一个跳到登录页面的方法

        return  shiroFilterFactoryBean;
    }
}
