package com.learn.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 用于抽取dao中的重复代码
 *      不同的DAO都需要有 jdbcTemplate引用 和 set方法
 *
 *      DaoImpl继承该类
 */
public class JdbcDaoSupport {

    //------------------- 生成对象，传jdbcTemplate 或 ds都会给 jdbcTemplate 赋值     -----------------------

    /**
     * set 注入 1：直接 jdbcTemplate
     */
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * set 注入 2：DataSource
     */
    private DataSource dataSource;

    //set
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        if (jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }

    //new
    private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
