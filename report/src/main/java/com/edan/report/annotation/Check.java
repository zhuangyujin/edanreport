package com.edan.report.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//针对方法参数进行校验
@Target(ElementType.METHOD)
//运行时需要
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
}
