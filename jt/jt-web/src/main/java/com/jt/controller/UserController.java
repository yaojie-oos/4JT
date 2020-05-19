package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user/")
public class UserController {
	//导入dubbo的用户接口
	@Reference(timeout = 3000,check = false)
	private DubboUserService dubboUserService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("{page}")
	public String index(@PathVariable String page) {
		
		return page;
	}
	
	@RequestMapping("doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		try {
			dubboUserService.save(user);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("doLogin")
	@ResponseBody
	public SysResult login(User user,HttpServletResponse response) {
		try {
			String token = dubboUserService.findUserByUP(user);
			//判断数据是否正确
			if(!StringUtils.isEmpty(token)) {
				//将数据保存到cookie中
				//cookie中的key固定值JT_TICKET
				Cookie cookie = new Cookie("JT_TICKET", token);
				cookie.setMaxAge(7*24*3600);//生命轴器
				cookie.setPath("/");//表示cookie的权限
				cookie.setDomain("jt.com");//要求所有d的 xxxx.jt.com,实现cookie共享
				//利用response对象写入客户端
				response.addCookie(cookie);
				return SysResult.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.fail();
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		String token = null;
		Cookie[] cookies = request.getCookies();
		for(Cookie c :cookies) {
			if("JT_TICKET".equals(c.getName())) {
				token = c.getValue();
				c.setMaxAge(0); 
				break;
			}
		}
		if(!StringUtils.isEmpty(token)) {
			jedisCluster.del(token);
			Cookie cookie = new Cookie("JT_TICKET","");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain("jt.com");
			response.addCookie(cookie);
		}
		return "redirect:/";
	}
	
	

	
	
}
