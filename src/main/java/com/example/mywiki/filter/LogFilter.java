package com.example.mywiki.filter;

/**
 * 日志过滤器，打印代码耗时
 * Created by tangssst@qq.com on 2021/06/09
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
 public class LogFilter implements Filter {

     private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

     @Override
     public void init(FilterConfig filterConfig) throws ServletException {

     }

     @Override
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         // 打印请求信息
         HttpServletRequest request = (HttpServletRequest) servletRequest;
         LOG.info("------------- LogFilter 开始 -------------");
         LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
         LOG.info("远程地址: {}", request.getRemoteAddr());
         //打印接口耗时
         long startTime = System.currentTimeMillis();   //开始时间
         filterChain.doFilter(servletRequest, servletResponse);
         LOG.info("------------- LogFilter 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
     }
 }

