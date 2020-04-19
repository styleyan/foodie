package com.iswn.mapper;

import com.iswn.pojo.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusMapper {
    /**
     * 添加订单状态
     * @param orderStatus
     */
    void insertOrderStatus(OrderStatus orderStatus);
}