package com.iswn.controll;

import com.iswn.bo.SubmitOrderBO;
import com.iswn.enums.PayMethodEnum;
import com.iswn.exception.http.RequestBadException;
import com.iswn.service.OrderService;
import com.iswn.utils.CookieUtils;
import com.iswn.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单相关控制器
 */
@RestController
@RequestMapping("/api/orders")
public class OrdersController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    public JsonResult create(
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info(submitOrderBO.toString());
        Integer payType = submitOrderBO.getPayMethod();
        if (payType != PayMethodEnum.ALIPAY.getType() && payType != PayMethodEnum.WEIXIN.getType()) {
            throw new RequestBadException("支付方式不支持");
        }

        // 1. 创建订单
        String orderId = orderService.createOrder(submitOrderBO);
        // 2. 创建订单以后，移除购物车中已结算(已提交)的商品
        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        // TODO: 整合 redis 之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据
        return JsonResult.success(orderId);
    }
}
