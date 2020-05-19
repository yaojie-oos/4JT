package com.jt;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

//测试hash/list/事务控制
public class TestRedis2 {
	@Test
	 public void testHash() {
		 Jedis jedis = new Jedis("192.168.112.129", 6379);
		 jedis.hset("user1", "id", "200");
		 jedis.hset("user1", "name", "姚杰");
		 jedis.hset("user1", "gender", "男");
		 System.out.println(jedis.hget("user1", "name"));
		 System.out.println(jedis.hgetAll("user1")); 
	 }
	
	//编辑list集合
	@Test
	public void list() {
		 Jedis jedis = new Jedis("192.168.112.129", 6379);
		 jedis.lpush("list2", "1","2","3");
		 String rpop = jedis.rpop("list2");
		 System.out.println(rpop);
	}
	
	//redis事务控制
	@Test
	public void transaction() {
		 Jedis jedis = new Jedis("192.168.112.129", 6379);
		 Transaction multi = jedis.multi();//开启事务
		try {
			 multi.set("ww","ww");
			 multi.set("dd","dd");
			 multi.exec();
		} catch (Exception e) {
			e.printStackTrace();
			multi.discard();
		}
	}
	
}
