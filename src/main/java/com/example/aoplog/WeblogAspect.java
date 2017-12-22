package com.example.aoplog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 日志记录器
 *
 * @author tianfeng
 * @date 2017/12/22
 */
@Aspect
@Component
public class WeblogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(WeblogAspect.class);

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution(public * com.example..controller.*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        //请求数据
        final HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        LOG.info("请求地址 : " + request.getRequestURL().toString());

        //请求头
        final Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            final String headerName = headers.nextElement();
            final String headerValue = request.getHeader(headerName);
            LOG.info("header: headerValue---> " + headerName + ", headerValue ---> " + headerValue);
        }

        LOG.info("HTTP METHOD : " + request.getMethod());
        LOG.info("IP : " + request.getRemoteAddr());
        LOG.info("PORT: " + request.getRemotePort());
        LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        LOG.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * returning的值和doAfterReturning的参数名一致
     *
     * @param ret Object
     * @return void
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {

        /**
         * 处理完请求，返回内容
         * 这里直接调用的是toString
         */
        LOG.info("返回值 :{} ，对应的class: {} ", ret, ret.getClass().getName());
    }

    /**
     * 环绕执行
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        final long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        final Object ob = pjp.proceed();
        LOG.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}
