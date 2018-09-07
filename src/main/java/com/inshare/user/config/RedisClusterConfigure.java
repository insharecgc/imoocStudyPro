package com.inshare.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Guichao
 * @since 2018/9/6
 */
//@Configuration
public class RedisClusterConfigure {

//    @Bean
    public JedisCluster getRedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.1.104",7001));
        jedisClusterNodes.add(new HostAndPort("192.168.1.104",7002));
        jedisClusterNodes.add(new HostAndPort("192.168.1.104",7003));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,5000,1000);
        return jc;
    }
}
