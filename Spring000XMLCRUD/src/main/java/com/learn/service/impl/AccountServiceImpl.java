package com.learn.service.impl;

import com.learn.dao.AccountDao;
import com.learn.domain.Account;
import com.learn.service.AccountService;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    /**
     * 需要Spring提供注入,我们提供set方法,这里是xml配置
     * 注解配置没set也行
     * @param accountDao
     */
    public void setAccountDao(AccountDao accountDao) {
System.out.println("dao="+accountDao);
        this.accountDao = accountDao;
    }



    public List<Account> findAllAccount() {
System.out.println("dao="+accountDao);
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer accountId) {
System.out.println("dao="+accountDao);
        return accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
System.out.println("dao="+accountDao);
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
System.out.println("dao="+accountDao);
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
System.out.println("dao="+accountDao);
        accountDao.deleteAccoutn(accountId);
    }

}
