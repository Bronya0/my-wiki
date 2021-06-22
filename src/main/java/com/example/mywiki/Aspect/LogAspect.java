package com.example.mywiki.Aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义一个切点，作用于所有的controller的所有的.Controller的所有方法（所有参数）
     */
    @Pointcut("execution(public * com.example.*.controller..*Controller.*(..))")
    public void controllerPointcut() {
    }

    /**
     * 前置通知before，业务代码之前执行，joinPoint是业务代码的参数
     * @param joinPoint
     * @throws Throwable
     */
    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // 打印请求信息
        LOG.info("------------- 开始 -------------");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        LOG.info("请求参数: {}", mapper.writeValueAsString(args));

        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        LOG.info("请求参数: {}", mapper.writeValueAsString(arguments));
    }

    /**
     * 环绕通知Around，在业务内容前面和后面执行
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //前面
        long startTime = System.currentTimeMillis();
        //业务代码
        Object result = proceedingJoinPoint.proceed();
        //后面
        ObjectMapper mapper = new ObjectMapper();
        LOG.info("返回结果: {}", mapper.writeValueAsString(result));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}

