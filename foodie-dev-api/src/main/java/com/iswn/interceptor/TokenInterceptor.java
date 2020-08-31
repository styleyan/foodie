package com.iswn.interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iswn.utils.JsonResult;
import com.iswn.utils.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaofei.yan
 * @Create 2020-08-31 17:04
 * @Descript 自定义 token 拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String tonken = request.getHeader("accessToken");

        // token 不存在
        if (tonken != null) {
            // 验证 token 是否正确
            boolean result = TokenUtils.verify(tonken);

            if (result) {
                return true;
            }
        }
        JsonResult jsonResult = JsonResult.failure(1003, "token不能为空");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        /**
         * 这里使用了 response.getOutPutStream()，提示 "提示出错： java.io.CharConversionException: Not an ISO 8859-1 character:" 异常，
         * 其实是 ISO-8859-1 为单字节编码，主要表示英文字符，无法正确表示中文
         */
        response.getWriter().println(JSON.toJSONString(jsonResult));
        return false;
    }
}
