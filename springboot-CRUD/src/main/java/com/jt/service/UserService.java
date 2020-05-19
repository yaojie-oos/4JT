package com.jt.service;

import java.util.List;

import com.jt.pojo.User;

public interface UserService {

	List<User> findAll();

	void insert(User user);

	void update(User user);

	void delete(String id);

}
