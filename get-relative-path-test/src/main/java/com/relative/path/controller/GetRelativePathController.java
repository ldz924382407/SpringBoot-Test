package com.relative.path.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * SpringBoot获取项目文件的相对路径
 * @Author 211145187
 * @Date 2022/2/22 19:43
 **/
@Controller
public class GetRelativePathController {

    @GetMapping(value = "/test")
    @ResponseBody
    public void index1(HttpServletRequest request) throws FileNotFoundException {
        //等同    request.getServletContext().getRealPath("")=request.getServletContext().getRealPath("/")
        //方法获得的路径不是项目路径，而是c盘下一个tomcat目录路径）  比如:C:\Users\211145187\AppData\Local\Temp\tomcat-docbase.8888.12632778012386910853\
        System.out.println(request.getServletContext().getRealPath(""));
        //方法获得的路径不是项目路径，而是c盘下一个tomcat目录路径）  比如:C:\Users\211145187\AppData\Local\Temp\tomcat-docbase.8888.12632778012386910853\
        System.out.println(request.getServletContext().getRealPath("/"));
        //实际获取的都是编译包里的根据经   比如：xx.class.getResource("").getPath()=xx.class.getResource("/").getPath()=xx.class.getClassLoader().getResource("").getPath()=ResourceUtils.getURL("classpath:").getPath()
        //获取该文件的相对路径    比如：/E:/WorkSpace/demo/target/classes/com/example/demo/controller/
        System.out.println(GetRelativePathController.class.getResource("").getPath());
        //获取编译包里的根路径    比如：/E:/WorkSpace/demo/target/classes/
        System.out.println(GetRelativePathController.class.getResource("/").getPath());
        //获取编译包里的图片路径    比如：/E:/WorkSpace/demo/target/classes/static/%e5%b0%bc%e5%85%8b.jpg
        System.out.println(GetRelativePathController.class.getClassLoader().getResource("static/尼克.jpg").getPath());
        //获取编译包里的根路径    比如：/E:/WorkSpace/demo/target/classes/
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
    }
}
