package com.jt.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//将user对象交给spring容器管理
@Component

public class User implements Serializable{
	private static final long serialVersionUID = 616187837503327132L;
	@Value("${user.id}")//从容器中取值然后赋值
	private Integer id;
	@Value("${user.username}")
	private String name;
	@Value("${user.age}")
	private Integer age;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
}
