package com.example.conf;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class AopConfig {

    @Around("@within(org.springframework.stereotype.Controller)")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            System.err.println("args: " + Arrays.asList(args));
            Object result = pjp.proceed();
            System.err.println("return : " + result);
            return result;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }
}
