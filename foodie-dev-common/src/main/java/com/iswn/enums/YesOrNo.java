package com.iswn.enums;

import lombok.Getter;

/**
 * 性别枚举
 */
@Getter
public enum YesOrNo {
    YES(1, "是"),
    NO(0, "否");

    private int type;
    private String value;

    YesOrNo(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
