package com.learn.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类,它包含了:开启事务,提交事务,回滚事务 和 释放连接
 */
public class TransactionManager {

    /**
     * 用于获取当前线程上的connection
     * 等着Spring给我们注进来
     */
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
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
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();//连接 还回 池中
            connectionUtils.removeConnection();//解除 绑定
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
