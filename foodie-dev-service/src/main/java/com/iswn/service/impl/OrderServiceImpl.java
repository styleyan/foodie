package com.iswn.service.impl;

import com.iswn.bo.AddressBO;
import com.iswn.bo.SubmitOrderBO;
import com.iswn.enums.OrderStatusEnum;
import com.iswn.enums.YesOrNoEnum;
import com.iswn.mapper.*;
import com.iswn.pojo.*;
import com.iswn.service.ItemsSpecService;
import com.iswn.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 创建订单
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private ItemsSpecService itemsSpecService;


    @Override
    public void createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethods = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        // 包邮费用设置为0
        Integer postAmount = 0;

        /**
         * 查询用户收货地址
         */
        AddressBO addressBO = new AddressBO();
        addressBO.setUserId(userId);
        addressBO.setAddressId(addressId);
        UserAddress userAddress = userAddressMapper.queryUserAddress(addressBO);

        // 1. 新订单数据保存
        String orderId = Sid.nextShort();
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        newOrder.setReceiverAddress(
                userAddress.getProvince()
                + "" + userAddress.getCity()
                + "" + userAddress.getDistrict()
                + "" + userAddress.getDetail()
        );

        newOrder.setPostAmount(postAmount);
        newOrder.setPayMethod(payMethods);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNoEnum.NO.getType());
        newOrder.setIsDelete(YesOrNoEnum.NO.getType());
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2. 循环根据itemSpecIds保存订单商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        // 商品原价累计
        Integer totalAmount = 0;
        // 优惠后的实际支付价格累计
        Integer realPayAmount = 0;

        // 一个订单下，多个商品操作
        for (String itemSpecId : itemSpecIdArr) {
            // TODO: 整合 redis后，商品购买的数量重新从 redis 的购物车中获取
            int buyCounts =1 ;

            // 2.1 根据规格id，查询规格的具体信息，主要是为了获取价格
            ItemsSpec itemSpec = itemsSpecMapper.queryItemSpecById(itemSpecId);
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id, 获取商品信息以及商品图片
            String itemId = itemSpec.getItemId();
            Items item = itemsMapper.getItemById(itemId);
            ItemsImg itemsImg = itemsImgMapper.queryItemMainImgById(item.getId());
            String imgUrl = itemsImg.getUrl();

            // 2.3 循环保存子订单数据到数据库
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(Sid.nextShort());
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemSpec.getName());
            subOrderItem.setPrice(itemSpec.getPriceDiscount());

            orderItemsMapper.insertOrderItem(subOrderItem);

            // 2.4 在用户提价订单以后，规格表中需要扣除库存
            itemsSpecService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        // 设置总价格
        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        // 保存订单
        ordersMapper.insertOrder(newOrder);

        // 3. 保存订单状态
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.getType());
        waitPayOrderStatus.setCreatedTime(new Date());

        orderStatusMapper.insertOrderStatus(waitPayOrderStatus);
    }
}
