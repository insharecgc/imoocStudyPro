package com.inshare.user.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 分布式锁，可用于秒杀、IP访问限制、ID自增
 * @author Guichao
 * @since 2018/9/7
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 默认超时时长为10秒
    private int expireMsec = 10 * 1000;

    private volatile boolean locked = false;

    private volatile String expiresTimeStr;

    public boolean lock(String key) {
        long expires = System.currentTimeMillis() + expireMsec + 1;
        String expiresStr = String.valueOf(expires);
        if (redisTemplate.opsForValue().setIfAbsent(key, expiresStr)) {
            locked = true;
            expiresTimeStr = expiresStr;
            return true;
        }
        // 判断是否超时，如果超时其他线程可获取锁
        String currentValueStr = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValueStr) &&
                Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
            String oldValueStr = redisTemplate.opsForValue().getAndSet(key, expiresStr);
            // 分布式下，如果多个线程都到这里，但是只有一个线程的设置值和当前值相同，他才能够获取锁
            if (!StringUtils.isEmpty(oldValueStr) && oldValueStr.equals(currentValueStr)) {
                locked = true;
                expiresTimeStr = expiresStr;
                return true;
            }
        }
        return false;
    }

    public void unlock(String key) {
        if (locked && expiresTimeStr.equals(redisTemplate.opsForValue().get(key))) {
            locked = false;
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }

    public void setExpireMsec(int expireMsec) {
        this.expireMsec = expireMsec;
    }
}
