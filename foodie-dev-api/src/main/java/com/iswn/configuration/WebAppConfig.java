package com.iswn.configuration;

import com.iswn.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaofei.yan
 * @Create 2020-08-31 17:17
 * @Descript 拦截器配置
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    /**
     * 由于拦截器在注解中无效，需要提交注入 bean
     */
    @Bean
    public HandlerInterceptor getTokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 所有以 api 开头的请求都需要经过 TokenInterceptor 校验
         * excludePathPatterns("", "", ...): 用于排除部分需要校验的链接地址, 需要指定请求全路径
         */
        registry.addInterceptor(getTokenInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");
    }
}
