package com.jt.service;

import com.jt.vo.SysResult;

public interface UserService {

	boolean check(String param, Integer type);

	void insert(String username, String password, String phone);

	SysResult login(String username, String password);

	String query(String token);

}
