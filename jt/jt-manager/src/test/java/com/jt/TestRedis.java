package com.jt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

import redis.clients.jedis.Jedis;

public class TestRedis {
	//string类型的操作方式
	@Test
	public void testString(){
		Jedis jedis = new Jedis("192.168.112.129", 6379);
		jedis.set("name", "姚杰");
		System.out.println(jedis.get("name"));
		
		//设定数据超时时间2中
		jedis.expire("name", 10);
		
	}
	
	@Test
	public void testTimeout() throws InterruptedException {
		Jedis jedis = new Jedis("192.168.112.129", 6379);
		jedis.setex("aaa", 2, "yaojie");
		
		System.out.println(jedis.get("aaa"));
		Thread.sleep(3000);
		//key不存在时操作正常，key存在时操作失败
		//分布式锁的问题
		jedis.setnx("aaa", "bbb");
		System.out.println(jedis.get("aaa"));
	}
	
	//实现对象转化为JSON
	@Test
	public void objectToJson() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(100L);
		ObjectMapper mapper = new ObjectMapper();
		//转化JSON是必须有get/set方法
		String json = mapper.writeValueAsString(itemDesc);
		System.out.println(json);
		
		//将json串转化为对象
		ItemDesc i= mapper.readValue(json, ItemDesc.class);
		System.out.println(i);
	}
	
	
	//实现List集合于json转换
	@SuppressWarnings("unchecked")
	@Test
	public void testListToJson() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(100L);
		ItemDesc itemDesc1 = new ItemDesc();
		itemDesc1.setItemId(100L);
		List<ItemDesc> list = new ArrayList<>();
		list.add(itemDesc);
		list.add(itemDesc1);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
		
		//将数据保存到redis中
		Jedis jedis = new Jedis("192.168.112.129", 6379);
		jedis.set("itemDescList", json);
		//从redis中获取数据
		String result = jedis.get("itemDescList");
		List<ItemDesc> readValue = mapper.readValue(result, list.getClass());
		System.out.println(readValue);
	}
	//利用redis保存业务数据 数据库
	//数据库数据：对象 Object
	//对象 --->  JSON --->字符串
}
