package com.vnborx.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationPointcut {
    @Before("execution(* com.vnborx.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("======= Before the method is executed =======");
    }

    @After("execution(* com.vnborx.service.UserServiceImpl.*(..)))")
    public void after() {
        System.out.println("======= After the method is executed =======");
    }

    @Around("execution(* com.vnborx.service.UserServiceImpl.*(..)))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Before Around");
        Signature signature = jp.getSignature();
        System.out.println("Signature: " + signature);
        Object proceed = jp.proceed();
        System.out.println("After Around");
    }
}
