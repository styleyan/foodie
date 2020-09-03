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

        if (shopCart.getItemId() == null) {
            throw new RequestBadException("商品不能为空");
        }
        String key = "shop_card:" + users.getId() + ":";
        String hkey = shopCart.getItemId();

        String redisShopCart = (String)RedisUtils.hashGet(key, hkey);


        if (redisShopCart != null) {
            ShopCart sc = JSONObject.parseObject(redisShopCart, ShopCart.class);
            shopCart.setBuyCounts(sc.getBuyCounts() + shopCart.getBuyCounts());
        }

        RedisUtils.hashPut("shop_card:" + users.getId() + ":", shopCart.getItemId(), JSON.toJSONString(shopCart));
        return JsonResult.success();
    }

    /**
     * 根据用户ID，查询购物车列表
     * @param userId
     * @return
     */
    @GetMapping("/query")
    public JsonResult query(@RequestParam("userId") String userId) {
        if (StringUtils.isAnyBlank(userId)) {
            throw new RequestBadException("用户id 不能为空");
        }

        String prefix = SHOP_CARD_PREV + userId + ":*";


        return JsonResult.success(prefix);
    }

    /**
     * 根据用户ID，合并购物车
     * @return
     */
    @PostMapping("/merge")
    public JsonResult mergeCart(@RequestBody ShopCartListBO shopcartListBO, HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");
        String userId = users.getId();

        if (users == null) {
            throw new RequestBadException("用户不能为空");
        }
        List<ShopCart> shopCartList = shopcartListBO.getList();

        for (ShopCart shopCart : shopCartList) {
            RedisUtils.hashPut("shop_card:" + userId + ":", shopCart.getItemId(), JSON.toJSONString(shopCart));
        }

        return JsonResult.success();
    }


    /**
     * 购物车中删除商品
     * @param userId
     * @param itemSpecId
     * @return
     */
    @PostMapping("/del")
    public JsonResult del(@RequestParam("userId") String userId,@RequestParam String itemSpecId) {
        if (StringUtils.isAnyBlank(userId, itemSpecId)) {
            throw new RequestBadException("比要参数不能为空");
        }

        return JsonResult.success();
    }
}
