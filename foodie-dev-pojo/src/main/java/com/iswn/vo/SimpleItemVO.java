package com.iswn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 6个最新商品的简单类型
 */
@Getter
@Setter
@ToString
public class SimpleItemVO {
    private String itemId;
    private String itemName;
    private String itemUrl;
}
