package com.iswn.controll;

import com.iswn.mapper.ItemsImgMapper;
import com.iswn.pojo.ItemsImg;
import com.iswn.utils.ImageDownUtils;
import com.iswn.utils.JsonResult;
import com.iswn.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei.yan
 * @Create 2020-08-18 14:58
 * @Descript
 */
@RestController
public class MytestController {
    @Autowired
    private ItemsImgMapper itemsImgMapper;

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
        RedisUtils.listPush((String)map.get("key"), map.get("map"));
        return "ok";
    }

    @PostMapping("/api/redis/setzset")
    public String setZset(@RequestBody Map map) {
        RedisUtils.listPush((String)map.get("key"), map.get("map"));

        return "ok";
    }

    @PostMapping("/api/redis/sethash11")
    public String sethash11(@RequestBody List<String> list) {
        System.out.println(list);

        list.forEach((val) -> {
            System.out.println(val);
        });
        return "ok";
    }

    @PostMapping("/api/donload/img")
    public JsonResult domwImage() {
        List<ItemsImg> itemsImgs = itemsImgMapper.queryAll();
        List<String> errorList = new ArrayList<>();
        int downNum = 0;

        for (ItemsImg itemsImg : itemsImgs) {
            String url = itemsImg.getUrl();
            Boolean result = ImageDownUtils.start(url);
            if (!result) {
                errorList.add(url);
            }
            downNum++;
            System.out.println(downNum);
        }

        return JsonResult.success(errorList);
    };
}
