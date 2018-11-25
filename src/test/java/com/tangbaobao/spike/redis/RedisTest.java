package com.tangbaobao.spike.redis;

import com.tangbaobao.spike.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tangxuejun
 * @version 2018/10/9 3:46 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedis() {
        User user = new User();
        user.setId(0L);
        user.setNickName("tanaobnao");
        redisTemplate.opsForValue().set("tangdaaoba",user);
    }

    @Test
    public void fun2() {
        get("tangbaobao");
    }
    public void get(String user) {
        System.out.println(user);
    }

    private String getStaring() {
        return "waah";
    }
}
