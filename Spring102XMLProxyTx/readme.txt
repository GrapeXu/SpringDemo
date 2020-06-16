Xml下的事务 转账 ——> 动态代理实现事务

BeanFactory

bean.xml
      accountService:
            - txManager

    + beanFactory:
            + accountService
            + txManager

    + proxyService