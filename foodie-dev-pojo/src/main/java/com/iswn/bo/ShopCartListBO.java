package com.iswn.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iswn.pojo.ShopCart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Y.jer
 */
@Getter
@Setter
@ToString
public class ShopCartListBO {
     /**
      * 以下注解可以把单个数据改成array数组
      * @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
      */
    List<ShopCart> list;
}
