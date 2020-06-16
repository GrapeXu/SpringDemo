package com.learn.utils;

import javafx.beans.binding.ObjectExpression;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类,它包含了:开启事务,提交事务,回滚事务 和 释放连接
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    /**
     * 用于获取当前线程上的connection
     * 等着Spring给我们注进来
     */
    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.learn.service.impl.*.*(..))")
    private void pt1() {}

//    public void setConnectionUtils(ConnectionUtils connectionUtils) {
//        this.connectionUtils = connectionUtils;
//    }

    /**
     * 开启事务
     */
//    @Before("pt1()")
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
//    @AfterReturning("pt1()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
//    @AfterThrowing("pt1()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
//    @After("pt1()")
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();//连接 还回 池中
            connectionUtils.removeConnection();//解除 绑定
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object arroundAdvice(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.开启事务
            this.beginTransaction();
            //3.执行方法
            rtValue = pjp.proceed(args);
            //4.提交事务
            this.commit();

            //返回结果
            return rtValue;
        }  catch (Throwable throwable) {
            //5.回滚事务
            this.rollback();
            throw new RuntimeException(throwable);
        } finally {
            //6.释放资源
            this.release();
        }
    }

}
