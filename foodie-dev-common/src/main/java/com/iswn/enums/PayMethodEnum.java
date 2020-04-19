package com.iswn.enums;

import lombok.Getter;

/**
 * 支付方式枚举
 */
@Getter
public enum PayMethodEnum {
    WEIXIN(1, "微信"),
    ALIPAY(2, "支付宝");

    private int type;
    private String value;

    PayMethodEnum(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
