package com.jt.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	public List<User> findAll(){
		System.out.println("服务一");
		return userMapper.selectList(null);
	}
	
	public void save(User user) {
		userMapper.insert(user);
	}
	
}
