package com.iswn.controll;

import com.alibaba.fastjson.JSON;
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
     * @param shopcartListBO
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody ShopCartListBO shopcartListBO, HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");
        if (users == null) {
            throw new RequestBadException("用户不能为空");
        }
        logger.error(JSON.toJSONString(shopcartListBO));
        ShopCart shopCart = shopcartListBO.getList().get(0);

        RedisUtils.hashPut("shop_card:" + users.getId(), shopCart.getItemId(), JSON.toJSONString(shopCart));
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
    public JsonResult mergeCart(@RequestBody Map map, HttpServletRequest request) {
        Users users = (Users)request.getAttribute("user");

        if (users == null) {
            return JsonResult.failure(ErrorCodeEnum.BAD_NOT_LOGIN.getCode(), ErrorCodeEnum.BAD_NOT_LOGIN.getMessage());
        }

        logger.info("info", map);
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
