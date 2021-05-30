package com.SpringBoot.aspect;

import cn.hutool.core.date.DateUtil;
import com.SpringBoot.annotation.Log;
import com.SpringBoot.bean.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @title: LogAspect
 * @Author HuangYan
 * @Date: 2021/3/10 10:26
 * @Version 1.0
 * @Description: 日志切面
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切点
     */
    @Pointcut("@annotation(com.SpringBoot.annotation.Log)")
    public void logPointCut(){}

    /**
     * 返回通知
     * @param joinPoint
     */
    @AfterReturning(value = "logPointCut()")
    public void saveLog(JoinPoint joinPoint){
        // 获取目标方法上的注解
        Log logAnnotation = getLogAnnotation(joinPoint);

        // 获取session中的user对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        User user = (User) request.getSession().getAttribute("user");

        // 封装数据存入数据库
        log.info("{} ===> {}正在{}{}的数据",  DateUtil.now(), user.getName(), logAnnotation.businessType().getDisc(), logAnnotation.moduleName());
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
