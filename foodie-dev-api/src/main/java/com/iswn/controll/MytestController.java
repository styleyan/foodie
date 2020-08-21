package com.iswn.controll;

import com.iswn.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xiaofei.yan
 * @Create 2020-08-18 14:58
 * @Descript
 */
@RestController
public class MytestController {
    @GetMapping("/api/redis/setvalue")
    public String helloSet(@RequestParam String key, @RequestParam String value) {
        RedisUtils.setValue(key, value);
        return "ok";
    }

    @GetMapping("/api/redis/getvalue")
    public String helloGet(@RequestParam String key) {
        String result = (String)RedisUtils.getValue(key);;
        return result;
    }

    @PostMapping("/api/redis/sethash")
    public String setHash(@RequestBody Map map) {
        RedisUtils.hashPutAll((String)map.get("key"), (Map)map.get("map"));
        return "ok";
    }

    @PostMapping("/api/redis/setlist")
    public String setList(@RequestBody Map map) {
        RedisUtils.listPush((String)map.get("key"), map.get("map"));
        return "ok";
    }

    @PostMapping("/api/redis/setset")
    public String setSet(@RequestBody Map map) {
//        RedisUtils.listPush((String)map.get("key"), map.get("map"));
        return "ok";
    }

    @PostMapping("/api/redis/setzset")
    public String setZset(@RequestBody Map map) {
//        RedisUtils.listPush((String)map.get("key"), map.get("map"));

        return "ok";
    }
}
