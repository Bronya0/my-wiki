package com.example.mywiki.config;

import com.example.mywiki.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;


    /**
     * 注册登录拦截器
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 拦截的请求
                .addPathPatterns("/**")
                // 不拦截的请求（放行）
                .excludePathPatterns(
                        "/test/**",
                        "/redis/**",
                        "/user/login",
                        "/category/all",
                        "/ebook/all",
                        "/ebook/search",
                        "/doc/all/**",
                        "/doc/vote/**",
                        "/doc/getContent/**"
                );

    }
}