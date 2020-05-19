package com.jt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestSentinel {
	//测试哨兵get/set操作
	//masterName 主机变量名称
	//sentinels Set<String> IP:端口
	@Test
	public void testSentinel() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add("192.168.112.129:26379");
		JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels);
		Jedis jedis = jedisSentinelPool.getResource();
		jedis.set("name", "yaojie");
		System.out.println(jedis.get("name"));
		jedis.close();
	}
}
