package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//1.标识主启动类 包含了包扫描，
//主启动类包路径就是包扫描路径
@SpringBootApplication
public class SpringBootRun {
	//2.编写main方法，实现类加载
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootRun.class, args);
	}
	
	
}
