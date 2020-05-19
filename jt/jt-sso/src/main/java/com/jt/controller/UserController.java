package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("check/{param}/{type}")
	public JSONPObject check(@PathVariable String param,@PathVariable Integer type,
									String callback) {
		
		JSONPObject jsonpObject = null;
		try {
			boolean flag = userService.check(param, type);
			jsonpObject = new JSONPObject(callback, SysResult.ok(flag));
		} catch (Exception e) {
			e.printStackTrace();
			jsonpObject = new JSONPObject(callback, SysResult.fail());
		}
		return jsonpObject;
		
	}
	
	@RequestMapping("register")
	public SysResult register(String username,String password,String phone) {
		try {
			userService.insert(username,password,phone);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("login")
	public SysResult register(String username,String password) {
		try {
			return userService.login(username,password);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("query/{token}")
	public JSONPObject query(@PathVariable String token,String callback) {
		String json = userService.query(token);
		if(StringUtils.isEmpty(json))
			return new JSONPObject(callback, SysResult.fail());
		else
			return new JSONPObject(callback, SysResult.ok(json));

	}
	
	
	
	
}
