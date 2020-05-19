package com.jt;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.pojo.User;

@SpringBootTest
public class TestLombok {
	@Test
	public void testSetGet() {
		User user = new User();
		user.setId(10).setAge(20).setName("yaojie").setSex("nana");
		System.out.println(user);
	}
}
