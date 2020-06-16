package com.learn.service.impl;

import com.learn.dao.AccountDao;
import com.learn.domain.Account;
import com.learn.service.AccountService;

import javax.sql.DataSource;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都是在业务层
 */
public class AccountServiceImpl  implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
System.out.println("transfer...");
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);

        System.out.println("??");

        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);

        accountDao.updateAccount(source);

//        int i = 1/0;

        accountDao.updateAccount(target);
    }
}
