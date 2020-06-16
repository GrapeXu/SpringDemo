package com.learn.JdbcTemplate;

import com.learn.dao.AccountDao;
import com.learn.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        //1. 获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2. 获取对象
        AccountDao accountDao = context.getBean("accountDao", AccountDao.class);

        System.out.println(accountDao);
        //3. 执行操作
        Account account = accountDao.findAccountById(1);
        System.out.println(account);
    }

}

















