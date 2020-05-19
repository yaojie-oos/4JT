package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.jt.service.UserService;
import com.jt.util.ObjectMapperUtils;
import com.jt.utils.HttpClientService;
import com.jt.vo.SysResult;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private HttpClientService httpClientService;

	@Override
	public void doRegister(String username, String password, String phone) {
		String url = "http://sso.jt.com/user/register";
		Map<String,String> params = new HashMap<>();
		String newPassword = DigestUtils.md5DigestAsHex(password.getBytes());
		params.put("username", username);
		params.put("password", newPassword);
		params.put("phone", phone);
   		String result = httpClientService.doPost(url, params);
   		SysResult sysResult = ObjectMapperUtils.toObject(result, SysResult.class);
   		if(sysResult.getStatus()==201) {
   			throw new RuntimeException();
   		}
	}

	@Override
	public void doLogin(String username, String password) {
		String url = "http://sso.jt.com/user/login";
		Map<String,String> params = new HashMap<>();
		String newPassword = DigestUtils.md5DigestAsHex(password.getBytes());
		params.put("username", username);
		params.put("password", newPassword);
		httpClientService.doPost(url, params);
	}

}
