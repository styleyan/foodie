package com.iswn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 展示商品搜索列表结果的VO
 */
@Getter
@Setter
@ToString
public class SearchItemsVO {
    /**
     * 商品id
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 销量
     */
    private Integer sellCounts;

    /**
     * 商品图片
     */
    private String imgUrl;

    /**
     * 商品价格
     */
    private Integer price;
}
