package com.jt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.controller.UserController;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCRUD {
	@Autowired //从容器中取值
	private UserController userController;
	

}
