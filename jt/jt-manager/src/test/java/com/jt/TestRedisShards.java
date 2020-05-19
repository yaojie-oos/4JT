package com.jt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisShards {
	@Autowired
	private ShardedJedis shardedJedis;
	/**
	 * 操作时需要将多台redis当作一台
	 */
	@Test
	public void testShards() {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("192.168.112.129", 6379);
		JedisShardInfo info2 = new JedisShardInfo("192.168.112.129", 6380);
		JedisShardInfo info3 = new JedisShardInfo("192.168.112.129", 6381);
		shards.add(info1);
		shards.add(info2);
		shards.add(info3);
		
		//操作分片redis对象工具类
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		shardedJedis.set("1902", "1902版");
		System.out.println(shardedJedis.get("1902"));
	}
	
	@Test
	public void testShardedJedis() {
		System.out.println(shardedJedis);
		shardedJedis.set("yaojie", "niu p");
		String string = shardedJedis.get("yaojie");
		System.out.println(string);
	}
}
