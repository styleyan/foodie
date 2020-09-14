package com.iswn.controll;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iswn.bo.ShopCartListBO;
import com.iswn.enums.ErrorCodeEnum;
import com.iswn.exception.http.RequestBadException;
import com.iswn.pojo.ShopCart;
import com.iswn.pojo.Users;
import com.iswn.utils.JsonResult;
import com.iswn.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Y.jer
 * 购物车相关接口
 */
@RestController
@RequestMapping("/api/shopcart")
public class ShopCartController {
    final static Logger logger = LoggerFactory.getLogger(ShopCartController.class);
    private static String SHOP_CARD_PREV = "shop_card:user_id_";
    /**
     * 添加商品到购物车，同步给后端存储
     * @param shopCart
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody ShopCart shopCart, HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");
        if (users == null) {
            throw new RequestBadException("用户不能为空");
        }

        if (shopCart == null || shopCart.getSpecId() == null) {
            throw new RequestBadException("商品不能为空");
        }
        String key = "shop_card:" + users.getId() + ":";
        String hkey = shopCart.getSpecId();

        String redisShopCart = (String)RedisUtils.hashGet(key, hkey);


        if (redisShopCart != null) {
            ShopCart sc = JSONObject.parseObject(redisShopCart, ShopCart.class);
            shopCart.setBuyCounts(sc.getBuyCounts() + shopCart.getBuyCounts());
        }

        RedisUtils.hashPut("shop_card:" + users.getId() + ":", shopCart.getSpecId(), JSON.toJSONString(shopCart));
        return JsonResult.success(true);
    }

    /**
     * 根据用户ID，合并购物车
     * @return
     */
    @PostMapping("/merge")
    public JsonResult mergeCart(@RequestBody Map<Object, ShopCart> map, HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");
        String userId = users.getId();

        if (users == null) {
            throw new RequestBadException("用户不能为空");
        }
        for (Object key : map.keySet()) {
            /**
             * 刚开始第一次居然用这么复杂的方式接收复杂对象
             * ShopCart shopCart = JSONObject.parseObject(JSONObject.toJSONString(map.get(key)), ShopCart.class);
             */
            ShopCart shopCart = map.get(key);
            String redisKey = "shop_card:" + userId + ":";
            String hkey = shopCart.getSpecId();

            /**
             * 获取redis 存储对象
             */
            String redisShopCart = (String)RedisUtils.hashGet(redisKey, hkey);


            if (redisShopCart != null) {
                ShopCart sc = JSONObject.parseObject(redisShopCart, ShopCart.class);
                shopCart.setBuyCounts(sc.getBuyCounts() + shopCart.getBuyCounts());
            }

            RedisUtils.hashPut("shop_card:" + users.getId() + ":", shopCart.getSpecId(), JSON.toJSONString(shopCart));
        }

        return JsonResult.success(true);
    }

    /**
     * 根据用户ID，查询购物车列表
     * @return
     */
    @GetMapping("/query")
    public JsonResult query(HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");

        if (users == null) {
            throw new RequestBadException("用户id 不能为空");
        }

        Map<Object, Object> cartMap = RedisUtils.hashGetAll("shop_card:" + users.getId() + ":");

        for (Object key : cartMap.keySet()) {
            ShopCart curShop = JSONObject.parseObject((String)cartMap.get(key), ShopCart.class);
            cartMap.put(key, curShop);
        }
        return JsonResult.success(cartMap);
    }

    /**
     * 购物车中删除商品
     * @param request
     * @param itemSpecIds
     * @return
     */
    @PostMapping("/del")
    public JsonResult del(HttpServletRequest request, @RequestBody ArrayList<Object> itemSpecIds) {
        Users users = (Users)request.getAttribute("user");

        if (users == null || itemSpecIds.size() <= 0) {
            throw new RequestBadException("必要参数不能为空");
        }
        RedisUtils.hashDeleteKeys("shop_card:" + users.getId() + ":", itemSpecIds);
        return JsonResult.success();
    }
}
