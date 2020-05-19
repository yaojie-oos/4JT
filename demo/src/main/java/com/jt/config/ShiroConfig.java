package com.jt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jt.entity.Shiro;

//该类中标识的就是xml中数据
@Configuration  //配置文件
public class ShiroConfig {
	@Bean
	public Shiro shiro() {
		return new Shiro();
	}
	
	
	
	
	
	
	
	
	
}
