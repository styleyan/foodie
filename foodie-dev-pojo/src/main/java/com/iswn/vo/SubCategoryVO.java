package com.iswn.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 三级分类查询字段
 */

@Getter
@Setter
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private Integer subType;
    private Integer subFatherId;
}
