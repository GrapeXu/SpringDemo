package com.learn.domain;

/**
 * 账户的实体类
 *
 *      变量 * 3
 *
 *      构造 * 2
 *
 *      getter + setter
 *
 *      toString()
 *
 */
public class Account {

    // ---------------------------------------------变量-------------------------------------------------

    private Integer id;
    private String name;
    private Float money;

    // ------------------------------------------constructor------------------------------------------------

    /**
     * 默认构造方法
     */
    public Account() {
    }

    /**
     *
     * @param id
     * @param name
     * @param money
     */
    public Account(Integer id, String name, Float money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    // ----------------------------------------getter and setter--------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }


    // ------------------------------------------- toString -------------------------------------------------

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
