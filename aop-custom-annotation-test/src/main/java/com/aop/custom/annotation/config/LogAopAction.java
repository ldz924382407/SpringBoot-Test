package com.aop.custom.annotation.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aop.custom.annotation.bean.OperateTeacherReq;
import com.aop.custom.annotation.enums.LogDetailEnums;
import com.aop.custom.annotation.response.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.MessageFormat;

@Aspect
@Component
public class LogAopAction {

    /**
     * AOP切面保存操作日志
     * @Author 211145187
     * @Around 注解描述的方法为一个环绕通知方法，在此方法中可以添加扩展业务逻辑，可以调用下一个切面对象或目标方法
     * @param point 连接点(此连接点只应用@Around描述的方法)
     * @Date 2022/5/16 14:38
     * @param point
     * @Return Response
     **/
    @Around("@annotation(com.aop.custom.annotation.config.LogAnnotation) && execution(* com.aop.custom.annotation.controller.*.*(..))")
    public Response logOperate(ProceedingJoinPoint point) throws NoSuchMethodException {
        //获取类的字节码对象，通过字节码对象获取方法信息
        Class<?> targetCls=point.getTarget().getClass();
        //获取方法签名(通过此签名获取目标方法信息)
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //获取目标方法上的注解指定的操作名称
        Method targetMethod=
                targetCls.getDeclaredMethod(
                        signature.getName(),
                        signature.getParameterTypes());
        System.out.println("获取目标方法上的注解指定的操作名称："+targetMethod);
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        String logModelType=logAnnotation.logModelType();
        System.out.println("获取自定义注解参数值logModelType：" + logModelType);
        //获取目标方法名(目标类型+方法名)
        String targetClsName=targetCls.getName();
        String targetObjectMethodName=targetClsName+"."+method.getName();
        System.out.println("获取目标方法名：" + targetObjectMethodName);
        //获取请求参数
        Object[] args = point.getArgs();
        //TODO 操作日志保存到数据库中
        String logDetailInfo = LogDetailEnums.getDetailByCode(logModelType);
        switch (LogDetailEnums.getLogDetailEnum(logModelType)) {
            case CREATE_ACCOUNT:
                OperateTeacherReq req = JSONObject.parseObject(JSON.toJSONString(args[0]), OperateTeacherReq.class);
                System.out.println("获取请求参数：" + req);
                JSONObject jsonObject = JSONObject.parseObject(req.toString());
                logDetailInfo = MessageFormat.format(logDetailInfo, req.getId(), req.getName(), LogDetailEnums.getDetailByCode("pstnFlag_" + jsonObject.getString("pstnFlag")));
                break;
            default:
                System.out.println("无该类型！");
                break;
        }
        return Response.success(logDetailInfo);
    }
}
