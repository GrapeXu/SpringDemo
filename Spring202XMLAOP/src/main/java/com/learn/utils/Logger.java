package com.learn.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类,它里面提供了公共的代码
 */
public class Logger {

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
    public void beforePrintLog() {
        System.out.println("前置通知 Logger类中的before print Log方法开始记录日志了...");
    }

    /**
     * 后置通知
     */
    public void afterReturnngPrintLog() {
        System.out.println("后置通知 Logger类中的after returning print Log方法开始记录日志了...");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知 Logger类中的after throw print Log方法开始记录日志了...");
    }

    /**
     * 最终通知
     */
    public void afterPrintLog() {
        System.out.println("最终通知 Logger类中的after print Log方法开始记录日志了...");
    }

    /**
     * 环绕通知
     *  问题：
     *      当我们配置了环绕通知之后，切入单方法没有执行，而通知方法执行了
     *  分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有 明确的 切入点调用，而我们的代码中没有
     *  解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，SPring框架会为我们提供该接口的实现类供我们使用
     *  Spring中的环绕通知：
     *      它是Spring框架为我们提供的一种可以在代码中手动控制增强方法合适执行的方式
     */
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
