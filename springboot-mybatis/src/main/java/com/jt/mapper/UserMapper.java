package com.jt.mapper;

import java.util.List;

import com.jt.pojo.User;

public interface UserMapper {
	
	//查询全部user表中的数据
	List<User> findAll();
	
}
