package com.learn.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类,它里面提供了公共的代码
 *      Spring注解 执行调用有问题 最终 在后置和异常的前面
 *      用环绕就没这个问题，自己写想咋就咋
 */
@Component("logger")
@Aspect//当前类是一个切面类
public class Logger {

    @Pointcut("execution(public * com.learn.service.impl.*.*(..))")
    private void pt1() {}

    /**
     * 用于打印日志:并且计划在其切入点方法执行之前执行
     *                          切入点方法就是业务层方法
     */
    public void printLog() {
        System.out.println("Logger类中的printLog方法开始记录日志了...");
    }

    /**
     * 前置通知
     */
    @Before("pt1()")
    public void beforePrintLog() {
        System.out.println("前置通知 Logger类中的before print Log方法开始记录日志了...");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt1()")
    public void afterReturnngPrintLog() {
        System.out.println("后置通知 Logger类中的after returning print Log方法开始记录日志了...");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知 Logger类中的after throw print Log方法开始记录日志了...");
    }

    /**
     * 最终通知
     */
    @After("pt1()")
    public void afterPrintLog() {
        System.out.println("最终通知 Logger类中的after print Log方法开始记录日志了...");
    }


    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            System.out.println("环绕通知 前置 Logger类中的around print Log方法开始记录日志了...");
            Object[] args = pjp.getArgs();//得到方法执行所需的参数
            rtValue = pjp.proceed(args);//明确调用业务层方法
            System.out.println("环绕通知 后置 Logger类中的around print Log方法开始记录日志了...");
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("环绕通知 异常 Logger类中的around print Log方法开始记录日志了...");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("环绕通知 最终 Logger类中的around print Log方法开始记录日志了...");
        }
    }
}
