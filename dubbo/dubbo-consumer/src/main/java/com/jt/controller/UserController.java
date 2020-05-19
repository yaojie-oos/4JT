package com.jt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.UserService;

@RestController
public class UserController {
	@Reference(timeout = 3000,check = false,loadbalance = "roundRobin")
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping("/save/{name}/{age}/{sex}")
	public String save(User user) {
		userService.save(user);
		return "成功";
	}
	
}