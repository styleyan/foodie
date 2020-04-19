package com.iswn.enums;

import lombok.Getter;

/**
 * @Desc: 商品评价登记枚举
 */
@Getter
public enum CommentLevelEnum {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    private int type;
    private String value;

    CommentLevelEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
