package com.aop.custom.annotation.controller;

import com.aop.custom.annotation.bean.OperateTeacherReq;
import com.aop.custom.annotation.config.LogAnnotation;
import com.aop.custom.annotation.response.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 211145187
 * @Date 2022/5/7 11:34
 **/
@RestController
public class AopController {

    /**
     * 测试AOP通过@Around注解和自定义注解 =》 实现保存操作日志功能
     * @Author 211145187
     * @Date 2022/5/17 10:56
     * @param req
     * @Return
     **/
    @RequestMapping(value = "/operateLog", method = RequestMethod.POST)
    @LogAnnotation(logModelType = "CREATE_ACCOUNT")
    public Response operateLog(@RequestBody OperateTeacherReq req) {
        return Response.success(req);
    }
}
