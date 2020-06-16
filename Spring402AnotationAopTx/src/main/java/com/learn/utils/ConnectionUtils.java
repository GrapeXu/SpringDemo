package com.learn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;


/**
 * 连接的工具类,它用于从数据源中获取一个连接,并且实现和线程的绑定
 * ThreadLocal - TransacationManager - service
 * 怎么确定当前线程的?
 */
@Component("connectionUtils")
public class ConnectionUtils {
    /**
     * 这里Thread是固定的,无需注入解耦
     */
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    /**
     * 不能new 或 自己创建;等着Spring为我们注入,xml方式提供set方法
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 用于注入
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     *      有,则返回
     *      无,则获取 绑定当前线程
     * @return
     */
    public Connection getThreadConnection() {
        try {
            //1.先从ThreadLocal上获取
            Connection conn = tl.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源中获取一个连接,并且 和线程绑定(存入ThreadLocal中)
                conn = dataSource.getConnection();
                //4.把conn存入ThreadLocal中
                tl.set(conn);
            }
            //5.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接 和 线程 解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}























