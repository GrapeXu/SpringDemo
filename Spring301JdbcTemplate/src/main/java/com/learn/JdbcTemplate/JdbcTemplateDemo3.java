package com.learn.JdbcTemplate;

import com.learn.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的CRUD操作
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        //1. 获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2. 获取对象
        JdbcTemplate jt = context.getBean("jdbcTemplate", JdbcTemplate.class);
        //3. 执行操作
//        jt.execute("insert into account(name, money) values('xxx', 2000)");

        //3.1保存
        jt.update("insert into account(name, money) values(?,?)", "eee", 3333);
        //3.2更新
        jt.update("update account set name=?, money=? where id=?", "test", 4567, 7);
        //3.3删除
        jt.update("delete from account where id=?", 8);
        //3.4查询所有
        // AccountRowMapper
//        List<Account> accounts = jt.query("select * from account where money > ?", new AccountRowMapper(), 1000f);
        //BeanPropertyRowMapper 可以返回对象或 集合
        List<Account> accounts = jt.query("select * from account where money > ?", new BeanPropertyRowMapper<Account>(Account.class), 1000f);
        for (Account account : accounts) {
            System.out.println(account);
        }
        //查询一个
        List<Account> accounts1 = jt.query("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), 1);
        System.out.println(accounts1.isEmpty()? "没有内容" : accounts1.get(0));
        //查询返回一行一列  使用聚合函数 但不加 group by子句
        Integer count = jt.queryForObject("select count(*) from account where money > ?", Integer.class,/*Long.class*/ 1000f);
        System.out.println(count);

    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {
    /**
     * 把结果集中的数据封装到Account中，然后由Spring把每个Account加到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
















