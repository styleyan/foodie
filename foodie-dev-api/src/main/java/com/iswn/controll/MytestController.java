package com.iswn.controll;

import com.iswn.utils.RedisUtils;
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
    @GetMapping("/api/test1/hello")
    public String helloSet(@RequestParam String key, @RequestParam String value) {
        RedisUtils.setValue(key, value);
        return "ok";
    }

    @GetMapping("/api/test1/hello/get")
    public String helloGet(@RequestParam String key) {
        String result = (String)RedisUtils.getValue(key);;
        return result;
    }
}
