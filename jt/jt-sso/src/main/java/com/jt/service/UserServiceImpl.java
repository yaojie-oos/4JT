package com.jt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jediscluster;

	@Override
	public boolean check(String param, Integer type) {
		String column = (type==1)?"username":((type==2)?"phone":"email");
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq(column, param);
		int count = userMapper.selectCount(queryWrapper);
		return count==0?false:true;
			
		
	}

	@Override
	public void insert(String username, String password, String phone) {
		User user = new User();
		user.setEmail(phone)
		.setPassword(password)
		.setUsername(username)
		.setPhone(phone)
		.setCreated(new Date())
		.setUpdated(user.getCreated());
		userMapper.insert(user);
		
	}

	@Override
	public SysResult login(String username, String password) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Map<String,String> params = new HashMap<>();
		params.put("username",username);
		params.put("password", password);
		queryWrapper.allEq(params);
		Integer count = userMapper.selectCount(queryWrapper);
		
		return count==1?SysResult.ok():SysResult.fail();
		
		
	}

	@Override
	public String query(String token) {
		return jediscluster.get(token);
		
	}
	
	
	
}
