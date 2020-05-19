package com.jt.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
@PropertySource("classpath:/properties/jediscluster.properties")
public class JedisClusterConfig {
	@Value("${jediscluster.nodes}")
	private String nodes;
	@Bean
	public JedisCluster jedisCluster() {
		String[] node = nodes.split(",");
		Set<HostAndPort> sets = new HashSet<>();
		for(String n : node) {
			String[] s = n.split(":");
			String host = s[0];
			String port = s[1];
			HostAndPort hostAndPort = new HostAndPort(host,Integer.parseInt(port));
			sets.add(hostAndPort);
		}
		JedisCluster jedisCluster = new JedisCluster(sets);
		return jedisCluster;
	}
}
