package com.phoenix.annotation_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 通知类
 */
//表示该类是一个通知类
@Aspect
public class MyAdvice {


    //统一配置切点
    @Pointcut("execution(* com.phoenix.service..*ServiceImpl.*(..))")
    public void pc() {
    }

    /**
     * 前置通知：
     *    目标方法运行之前调用
     * 后置通知（如果出现异常不会调用）
     *    在目标方法运行之后调用
     * 环绕通知：
     *    在目标方法之前和之后都调用
     * 异常拦截通知
     *    如果出现异常，就会调用
     * 后置通知（无论是否出现异常都会调用）
     *    在目标方法运行之后调用
     *
     */

    /**
     * 前置通知
     */
    //指定该方法是前置通知，并指定切入点
    @Before("MyAdvice.pc()")
    public void before() {
        System.out.println("这是前置通知2");
    }

    @AfterReturning("execution(* com.phoenix.service..*ServiceImpl.*(..))")
    public void afterReturning() {
        System.out.println("这是后置通知2");
    }

    @Around("execution(* com.phoenix.service..*ServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("这是环绕通知之前的部分2");
        Object proceed = proceedingJoinPoint.proceed();// 调用目标方法
        System.out.println("这是环绕通知之后的部分2");
        return proceed;
    }

    /**
     * 异常通知
     */
    @AfterThrowing("execution(* com.phoenix.service..*ServiceImpl.*(..))")
    public void afterException() {
        System.out.println("异常通知2");
    }

    /**
     * 后置通知，出现异常也会调用
     */
    @After("execution(* com.phoenix.service..*ServiceImpl.*(..))")
    public void afterAnyWay() {
        System.out.println("后置通知，出现异常也会调用2");
    }

}
