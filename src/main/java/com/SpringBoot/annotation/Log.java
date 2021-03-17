package com.SpringBoot.annotation;

import com.SpringBoot.enums.BusinessType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块名称
     * @return
     */
    String moduleName() default "";

    /**
     * 功能
     * @return
     */
    BusinessType businessType() default BusinessType.OTHER;

}
