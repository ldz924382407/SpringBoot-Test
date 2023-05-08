package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试shiro权限控制
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {
    //    加/代表默认跳到这
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg1","hello,Shiro");
        return "index";
    }
    @RequestMapping("/user/add")
    public String toAdd(){
        return "add";
    }
    @RequestMapping("/user/update")
    public String toUpdate(){
        return "update";
    }

    //跳到登录登录页面的方法
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //登录操作
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //使用shiro，编写认证操作
        //1. 获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2. 封装用户的数据,token是根据用户名和密码生成的
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token.setRememberMe(true); //记住我

        //

        //3. 执行登录的方法，只要没有异常就代表登录成功！
        try {
            subject.login(token); //执行登录，shiro帮我们弄的，很麻烦的,会跳到UserRealm的认证方法认证的
            //登录成功！返回首页
            return "index";
        }catch (UnknownAccountException e){
            //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    //假的批量查询
    @RequiresPermissions("sysconfig:edit:view")
    @ResponseBody
    @GetMapping("/queryList")
    public List queryList(){
        System.out.println("校验通过，执行查询操作！");
        List<String> list = new ArrayList();
        list.add("tom");
        list.add("jery");
        //查询数据库封装list
        return list;
    }
}
