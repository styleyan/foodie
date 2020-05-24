package com.iswn.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {
    final static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询所有列表
     * @return
     */
    @GetMapping("/set")
    public Object set(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    /**
     * 首页分类展示需求
     * @return
     */
    @GetMapping("/get")
    public String get(@RequestParam("key") String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    /**
     * 查询二级分类数据
     * @return
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("key") String key) {
        redisTemplate.delete(key);
        return "ok";
    }
}
