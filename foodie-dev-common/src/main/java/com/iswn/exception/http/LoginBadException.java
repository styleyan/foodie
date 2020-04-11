package com.iswn.exception.http;

import com.iswn.enums.ErrorCodeEnum;

/**
 * 登录错误异常
 */
public class LoginBadException extends FoodieException {
    /**
     * 无参异常
     */
    public LoginBadException(){
        this.code = ErrorCodeEnum.BAD_LOGIN.getCode();
        this.message = ErrorCodeEnum.BAD_LOGIN.getMessage();
    }

    /**
     * 有参异常
     * @param message
     */
    public LoginBadException(String message) {
        this.code = ErrorCodeEnum.BAD_LOGIN.getCode();
        this.message = message;
    }
}
