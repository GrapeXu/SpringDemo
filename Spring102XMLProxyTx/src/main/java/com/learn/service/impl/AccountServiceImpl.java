package com.learn.service.impl;

import com.learn.dao.AccountDao;
import com.learn.domain.Account;
import com.learn.service.AccountService;
import com.learn.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 * 加入事务管理操作
 * 事务管理 转移 到 BeanFactory
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    /**
     * 需要Spring提供注入,我们提供set方法,这里是xml配置
     * 注解配置没set也行
     * @param accountDao
     */
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccountById(Integer accountId) {
        accountDao.deleteAccountById(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        //2.执行操作
        List<Account> accounts = accountDao.findAllAccount();

        //2.1.根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2.根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3.转出账户 减钱
        source.setMoney(source.getMoney() - money);
        //2.4.转入账户 加钱
        target.setMoney(target.getMoney() + money);
        //2.5.更新转出账户
        accountDao.updateAccount(source);

//        int i = 1/0;

        //2.6.更新转入账户
        accountDao.updateAccount(target);

    }

}













