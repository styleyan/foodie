package com.iswn.exception;

import com.iswn.exception.http.FoodieException;
import com.iswn.utils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Y.jer
 * 统一错误拦击
 */

@ControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * http 请求错误拦截
     * @return
     */
    @ExceptionHandler(value = FoodieException.class)
    @ResponseBody
    public JsonResult httpHandException(HttpServletRequest req, FoodieException e) {
        return JsonResult.failure(e.getCode(), e.getMessage());
    }
}

