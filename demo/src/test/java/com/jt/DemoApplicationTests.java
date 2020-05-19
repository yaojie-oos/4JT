package com.jt;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.entity.Dog;
import com.jt.entity.Shiro;
import com.jt.entity.User;

@SpringBootTest//动态加载spring容器
class DemoApplicationTests {
	@Autowired
	private User user;
	@Autowired
	private Dog dog;
	@Autowired
	private Shiro shiro;
	@Test
	void contextLoads() {
		System.out.println(user);
		System.out.println(dog);
		//要求：获取user对象
		//ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext();
		//User bean = (User)ac.getBean("user");
		//bean.setName("姚杰");
	}

	@Test
	void testShiro() {
		shiro.pri();
	}
}
