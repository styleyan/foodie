package com.iswn.controll;

import com.iswn.bo.ShopcartBO;
import com.iswn.exception.http.RequestBadException;
import com.iswn.service.UsersService;
import com.iswn.utils.BeanUtils;
import com.iswn.utils.JsonResult;
import com.iswn.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
     * @param userId
     * @param shopcartBO
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestParam("userId") String userId, @RequestBody ShopcartBO shopcartBO) {
        if (StringUtils.isBlank(userId)) {
            throw new RequestBadException("用户id不能为空");
        }
        Map map = BeanUtils.beanToMap(shopcartBO);
        RedisUtils.hashPutAll(SHOP_CARD_PREV + userId +":" + shopcartBO.getSpecId(), map);
        return JsonResult.success();
    }

    @GetMapping("/query")
    public JsonResult query(@RequestParam("userId") String userId) {
        if (StringUtils.isAnyBlank(userId)) {
            throw new RequestBadException("用户id 不能为空");
        }

        String prefix = SHOP_CARD_PREV + userId + ":*";


        return JsonResult.success(prefix);
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
