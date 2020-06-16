dao
    AccountDao
    UserDao
    XxxDao

每个Dao都会有重复代码
    最基本的就是 jdbcTemplate + set注入方法

编写JdbcDaoSupport 作为父类，继承其公共部分。


bean.xml
    不再注册JdbcTemplate
    dao 不注入 JdbcTemplate 注入DataSource触发set方法来对JdbcTemplate初始化


Spring 其实 是有它的JdbcDaoSupport的 可以直接继承。用于去除dao的实现类中 注入和定义 jdbcTemplate的重复代码

自定义 不继承的方法 可以 类里放个引用 ，注解注入就完事了

继承 JdbcDaoSupport 用注解配置比较麻烦，引用在jar包里。其实也能，指定方法呗



一般认为 xml 适合继承
         注解 不适合
