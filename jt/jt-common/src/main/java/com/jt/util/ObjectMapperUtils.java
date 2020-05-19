package com.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;

//编辑工具类 实现对象与json转化
public class ObjectMapperUtils {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public static String toJson(Object object) {
		String json = null;
		try {
			json = MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			//将检查异常转化为运行时异常
			throw new RuntimeException();
		}
		return json;
	}
	
	public static <T>T toObject(String json,Class<T> cls) {
		T t = null;
		try {
			t= MAPPER.readValue(json, cls);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return t;
	}
}
