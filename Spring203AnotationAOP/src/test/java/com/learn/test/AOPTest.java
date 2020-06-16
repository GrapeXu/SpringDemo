package com.learn.test;

import com.learn.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP的配置
 */
public class AOPTest {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        AccountService service = (AccountService) context.getBean("accountService");
        //3.执行方法
        service.saveAccount();
    }
}














