package com.iswn.mapper;

import com.iswn.pojo.OrderItems;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsMapper {
    /**
     * 插入订单商品id
     * @param orderItems
     */
    void insertOrderItem(OrderItems orderItems);
}