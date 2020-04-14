package com.iswn.vo;

import com.iswn.pojo.Items;
import com.iswn.pojo.ItemsImg;
import com.iswn.pojo.ItemsParam;
import com.iswn.pojo.ItemsSpec;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情VO
 */
@Getter
@Setter
@ToString
public class ItemInfoVO {
    private Items item;
    private ItemsParam itemParam;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
}
