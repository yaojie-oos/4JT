package com.jt.service;


public interface UserService {

	void doRegister(String username, String password, String phone);

	void doLogin(String username, String password);

}