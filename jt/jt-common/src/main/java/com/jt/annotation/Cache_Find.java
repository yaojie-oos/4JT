package com.jt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//定义一个查询的注解

import com.jt.enu.KEY_ENUM;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache_Find {
	String key() default ""; //接受用户key的值
	//定义key的类型
	KEY_ENUM keyType() default KEY_ENUM.AUTO;
	
	int seconds() default 0; //永不失效
}
