package com.inshare.user.controller;

import com.inshare.user.entity.Result;
import com.inshare.user.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

/**
 * @author Guichao
 * @since 2018/9/6
 */
@RestController
@RequestMapping("redisCluster")
public class RedisClusterController {

    /*
    @Autowired
    private JedisCluster jedisCluster;

    @GetMapping("/set/{key}/{value}")
    public Result set(@PathVariable("key") String key, @PathVariable("value") String value) {
        String result = "NO";
        try {
            result = jedisCluster.set(key, value);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.success(result);
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        String value = jedisCluster.get(key);
        return value;
    }
    */
}
