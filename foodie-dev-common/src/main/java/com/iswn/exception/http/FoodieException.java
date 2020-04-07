package com.iswn.exception.http;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Y.jer
 * foodie相关业务统一异常
 */
@Getter
@Setter
public class FoodieException extends RuntimeException {
    protected int code;
    protected String message;
}
