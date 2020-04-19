package com.iswn.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Y.jer
 */
@Getter
@Setter
@ToString
public class ShopcartBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String buyCounts;
    private String priceDiscount;
    private String priceNormal;
}
