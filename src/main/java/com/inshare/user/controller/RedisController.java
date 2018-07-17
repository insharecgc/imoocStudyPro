package com.inshare.user.controller;

import com.inshare.user.entity.Result;
import com.inshare.user.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @RequestMapping(value = "/test")
    public Result redisTest() {
        strRedis.opsForValue().set("name", "我的名字Inshare", 60, TimeUnit.SECONDS);
        String name = strRedis.opsForValue().get("name");
        return ResultUtil.success(name);
    }
}
