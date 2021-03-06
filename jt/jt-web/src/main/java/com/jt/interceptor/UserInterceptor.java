package com.jt.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtils;
import com.jt.utils.UserThreadLocal;

import redis.clients.jedis.JedisCluster;
@Component
public class UserInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 业务逻辑
	 * 1.获取cookie数据
	 * 2.从众多cookie中获取token
	 * 3.判断redis缓存服务器中是否有数据
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截--------------------");
		String token  = null; 
		//1.获取cookie
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if("JT_TICKET".equals(c.getName())) {
				token = c.getValue();
				break;
			}
		}
		
		//2判断token是否有效
		if(!StringUtils.isEmpty(token)) {
			String json = jedisCluster.get(token);
			if(!StringUtils.isEmpty(json)) {
				User user = ObjectMapperUtils.toObject(json,User.class);
				//将对象出入request域中传给controller
				//request.setAttribute("JT_user", user);
				
				//将对象存入session中
				//request.getSession().setAttribute("JT_user", user);
				//在aftercompletion中关闭session
				
				//高级用法 使用threadLocal
				UserThreadLocal.set(user);
				return true;
			}
			
		}
		
		//3.重定向到用户登入页面
		response.sendRedirect("/user/login.html");
		return false;//表示拦截
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//request.getSession().removeAttribute("JT_user");
		UserThreadLocal.remove();
	}
	
	
}
