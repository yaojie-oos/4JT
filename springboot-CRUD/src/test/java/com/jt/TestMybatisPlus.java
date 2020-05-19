package com.jt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMybatisPlus {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setName("mybatisplus");
		user.setSex("nan");
		user.setAge(99);
		int rows = userMapper.insert(user);
		System.out.println(rows);
	}
	
	@Test
	public void testFind() {
		//将对象中不为null的属性当作where条件
		//方式1.使用对象封装
//		User user = new User();
//		user.setAge(18);
//		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		//方式2.使用字段赋值
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//测试等于 eq
		//queryWrapper.eq("age", 18).eq("name", "姚杰");
		//测试大于 gt  大于等于 ge
		//queryWrapper.gt("age", 18);
		//测试小于 lt	    小于等于 le
		queryWrapper.lt("age", 18);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	//数据更新 将年林为18岁的改为19岁
	@Test
	public void testUpdate() {
		User user = new User();
		user.setAge(19);
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("age", 18);
		userMapper.update(user, updateWrapper);
	}
	
	@Test
	public void deleteByAge() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("age", 19);
		userMapper.delete(queryWrapper);
	}
}
