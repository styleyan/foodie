package com.iswn.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Y.jer
 */
@Getter
@Setter
@ToString
public class ShopCart {
    /**
     * 商品id
     */
    private String itemId;
    /**
     * 商品图片地址
     */
    private String itemImgUrl;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 商品规格id
     */
    private String specId;
    /**
     * 规格名称
     */
    private String specName;
    /**
     * 购买数量
     */
    private String buyCounts;
    /**
     * 优惠价
     */
    private String priceDiscount;
    /**
     * 原价
     */
    private String priceNormal;
}
