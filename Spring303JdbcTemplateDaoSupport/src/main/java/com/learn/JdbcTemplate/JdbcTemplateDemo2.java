package com.learn.JdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        //1. 获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2. 获取对象
        JdbcTemplate jt = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //3. 执行操作
        jt.execute("insert into account(name, money) values('xxx', 2000)");
    }

}

















