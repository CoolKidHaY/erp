package com.SpringBoot.aspect;

import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @title: LogAspect
 * @Author HuangYan
 * @Date: 2021/3/10 10:26
 * @Version 1.0
 * @Description: 日志切面
 */
@Aspect
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.SpringBoot.annotation.Log)")
    public void logPointCut(){}

    /**
     * 返回通知
     * @param joinPoint
     */
    @AfterReturning
    public void doAfterReturning(JoinPoint joinPoint){
        // 获取目标方法上的注解
        Log logAnnotation = getLogAnnotation(joinPoint);

        // 获取session中的user对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        User user = (User) request.getSession().getAttribute("user");

        // 封装数据存入数据库

    }

    /**
     * 获取注解
     * @param joinPoint
     */
    private Log getLogAnnotation(JoinPoint joinPoint) {
        // 获取切点签名
        Signature signature = joinPoint.getSignature();
        // 将签名转换为方法签名
        MethodSignature methodSignature = (MethodSignature) signature;
        // 获取目标方法
        Method method = methodSignature.getMethod();
        // 获取方法上的Log注解
        return method.getAnnotation(Log.class);
    }

}
