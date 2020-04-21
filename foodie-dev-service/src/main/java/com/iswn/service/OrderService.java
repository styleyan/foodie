package com.iswn.service;

import com.iswn.bo.SubmitOrderBO;

public interface OrderService {
    /**
     * 用于创建订单相关信息
     * @param submitOrderBO
     */
    String createOrder(SubmitOrderBO submitOrderBO);
}
