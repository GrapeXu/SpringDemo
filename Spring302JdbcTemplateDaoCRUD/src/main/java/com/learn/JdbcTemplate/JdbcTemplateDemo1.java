package com.learn.JdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //准备数据源：Spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db");
        ds.setUsername("root");
        ds.setPassword("xxxx");

        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();//这里传ds也一样
        //1.1给jt设置数据源
        jt.setDataSource(ds);
        //2.执行操作
        jt.execute("insert into account(name, money) values('ccc', 1000)");
    }

}

















