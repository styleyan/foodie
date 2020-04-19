package com.iswn.controll;

import com.iswn.bo.ShopcartBO;
import com.iswn.exception.http.RequestBadException;
import com.iswn.service.UsersService;
import com.iswn.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Y.jer
 * 购物车相关接口
 */
@RestController
@RequestMapping("/api/shopcart")
public class ShopCartController {
    final static Logger logger = LoggerFactory.getLogger(ShopCartController.class);

    /**
     * 添加商品到购物车，同步给后端存储
     * @param userId
     * @param shopcartBO
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(
            @RequestParam("userId") String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StringUtils.isBlank(userId)) {
            throw new RequestBadException("用户id不能为空");
        }

        logger.info(shopcartBO.toString());
        // TODO: 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到 redis 缓存。

        return JsonResult.success();
    }


    /**
     * 购物车中删除商品
     * @param userId
     * @param itemSpecId
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/del")
    public JsonResult del(
            @RequestParam("userId") String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StringUtils.isAnyBlank(userId, itemSpecId)) {
            throw new RequestBadException("比要参数不能为空");
        }

        // TODO: 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品
        return JsonResult.success();
    }
}
