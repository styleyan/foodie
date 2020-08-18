package com.iswn.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaofei.yan
 * @Create 2020-08-18 14:58
 * @Descript
 */
@RestController
public class MytestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/api/test1/hello")
    public String helloSet(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @GetMapping("/api/test1/hello/get")
    public String helloGet(@RequestParam String key) {
        String result = (String)redisTemplate.opsForValue().get(key);
        return result;
    }
}
