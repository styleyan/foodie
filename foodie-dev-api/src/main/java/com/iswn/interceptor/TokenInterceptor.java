package com.iswn.interceptor;

import com.alibaba.fastjson.JSON;
import com.iswn.pojo.Users;
import com.iswn.utils.JsonResult;
import com.iswn.utils.RedisUtils;
import com.iswn.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaofei.yan
 * @Create 2020-08-31 17:04
 * @Descript 自定义 token 拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
    /**
     * 前置处理，执行 handler 之前执行该方法，返回true(放行)、返回false(拦截)
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");

        // 从 header 中获取 token值
        String token = request.getHeader("X-Requested-Token");

        // 如果没有token, 未登录状态直接放行
        if(StringUtils.isBlank(token)) {
            return true;
        }

        Users users = (Users)RedisUtils.getValue(token);

        if (users != null) {
            request.setAttribute("user", users);
            return true;
        }

        JsonResult jsonResult = JsonResult.failure(1003, "token不正确");
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
