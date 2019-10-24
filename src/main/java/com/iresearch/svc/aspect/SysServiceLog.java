package com.iresearch.svc.aspect;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
//注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysServiceLog {


    String project() default "";

    //操作模块
    String model() default "";

}