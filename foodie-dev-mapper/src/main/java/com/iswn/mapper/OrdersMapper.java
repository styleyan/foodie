package com.iswn.mapper;

import com.iswn.pojo.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper{
    /**
     * 增加订单
     * @param orders
     */
    void insertOrder(Orders orders);
}