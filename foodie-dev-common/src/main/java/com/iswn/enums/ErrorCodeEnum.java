package com.iswn.enums;

import lombok.Getter;

/**
 * @Author Y.jer
 * 错误码，对应表
 */
@Getter
public enum ErrorCodeEnum {
    BAD_REQUEST(2003, "参数错误"),
    BAD_LOGIN(2004, "登录异常"),
    BAD_NOT_LOGIN(2005, "用户暂未登录");
    private int code;
    private String message;

    /**
     * 设置枚举
     * @param code
     * @param message
     */
    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
