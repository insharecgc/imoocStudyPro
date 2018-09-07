package com.inshare.user.controller;

import com.inshare.user.entity.Girl;
import com.inshare.user.entity.Person;
import com.inshare.user.entity.Result;
import com.inshare.user.repository.PersonRepository;
import com.inshare.user.service.GirlService;
import com.inshare.user.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private GirlService girlService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StringRedisTemplate strRedis;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;

    @GetMapping("/test1")
    public Result redisTest1() {
        valOpsStr.set("name", "国海");
        return ResultUtil.success(valOpsStr.get("name"));
    }

    @GetMapping(value = "/test")
    public Result redisTest() {
        // 方式1
        valOpsStr.set("name", "guohai");
        valOpsStr.get("name");

        // 方式2（后面两个参数分别表示留存时间和秒，不要表示永久有效）
        Object girl = girlService.getGirl(1);
        valOpsObj.set(1, girl, 300, TimeUnit.SECONDS);
        girl = valOpsObj.get(1);

        // 方式3
        strRedis.opsForValue().set("name", "我的名字Inshare", 60, TimeUnit.SECONDS);
        String name = strRedis.opsForValue().get("name");
        return ResultUtil.success(name);
    }
}
