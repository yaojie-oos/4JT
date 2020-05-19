package com.jt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;

public interface UserMapper extends BaseMapper<User>{
	
	//查询全部user表中的数据
	List<User> findAll();
	
	int insert(User user);

	void update(User user);

	void delete(String id);
	
}
