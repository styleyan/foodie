package com.iswn.exception.http;

import com.iswn.enums.ErrorCodeEnum;

/**
 * 请求参数异常
 */
public class RequestBadException extends FoodieException {
    /**
     * 无参数异常
     */
    public RequestBadException() {
        this.code = ErrorCodeEnum.BAD_REQUEST.getCode();
        this.message = ErrorCodeEnum.BAD_REQUEST.getMessage();
    }

    /**
     * 自定义异常内容
     */
    public RequestBadException(String message) {
        this.code = ErrorCodeEnum.BAD_REQUEST.getCode();
        this.message = message;
    }
}
