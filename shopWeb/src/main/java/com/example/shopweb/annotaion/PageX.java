package com.example.shopweb.annotaion;

import java.lang.annotation.*;

@Target(ElementType.METHOD)             //应用于方法
@Retention(RetentionPolicy.RUNTIME)     //在运行时可以通过反射访问
@Documented         //该注解被包含在javaDoc文档中
public @interface PageX {
}
