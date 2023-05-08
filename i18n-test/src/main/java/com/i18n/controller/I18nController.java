package com.i18n.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 211145187
 * @Date 2022/2/25 13:54
 **/
@RestController
@Slf4j
@RequestMapping("/i18n")
public class I18nController {

    //首页,注意请求路径和页面名称不成重名，否则报错,Circular view path [hello]: would dispatch back to the current handler URL [/springmvc_01/hello] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
    @GetMapping(value = "")
    public ModelAndView internationalization() {
        return new ModelAndView("internationalization.html");
    }
}
