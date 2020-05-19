package com.jt.utils;

import com.jt.pojo.User;

//threadLocal是线程安全的
public class UserThreadLocal {
	/*
	 * threadlocal如何存取多个数据
	 * ThreadLocal<Map<k,v>> 利用map
	 */
	private static ThreadLocal<User> thread = new ThreadLocal<>();
	
	public static void set(User user) {
		thread.set(user);
	}
	
	public static User get() {
		return thread.get();
	}
	
	//使用threadlocal切记内存泄漏
	public static void remove() {
		thread.remove();
	}
}
