Xml基础转账
+

事务 手写ThreadLocal实现

实现事务控制
    ConnectionUtils 连接控制: 从数据源获取连接 + 线程&连接绑定
    TransactionManager 事务控制:开启 提交 回滚 释放
    AccountServiceImpl 加入事务管理操作

bean.xml
    QueryRunner 构造注入数据源,取消。改为无参构造
    ConnectionUtils set注入 数据源
    dao注入connectionUtils

    事务控制 由 持久层 回归到了 业务层


    bean中依赖关系混乱——Spring事务控制

方法依赖同样,多次调用。重复代码很多。—— 代理 —— AOP