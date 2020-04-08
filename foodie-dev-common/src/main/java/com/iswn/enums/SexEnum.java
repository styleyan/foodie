package com.iswn.enums;

import lombok.Getter;

/**
 * 性别枚举
 */
@Getter
public enum SexEnum {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    private int type;
    private String value;

    SexEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
