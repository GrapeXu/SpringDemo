package com.learn.service.impl;

import com.learn.dao.AccountDao;
import com.learn.domain.Account;
import com.learn.service.AccountService;
import com.learn.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 * 加入事务管理操作
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionManager txManager;

    /**
     * 需要Spring提供注入,我们提供set方法,这里是xml配置
     * 注解配置没set也行
     * @param accountDao
     */
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 需要Spring提供注入,我们提供set方法,这里是xml配置
     * 注解配置没set也行
     * @param txManager
     */
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public List<Account> findAllAccount() {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return accounts;
        } catch (Exception e) {
            //5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            Account account = accountDao.findAccountById(accountId);
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return account;
        } catch (Exception e) {
            //5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果

        } catch (Exception e) {
            //5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果

        } catch (Exception e) {
            //5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }

    }

    public void deleteAccountById(Integer accountId) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.deleteAccountById(accountId);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            //5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            txManager.release();
        }

    }

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("qqq");
        try {
            //1.开启事务
            txManager.beginTransaction();
            System.out.println("aa");
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

        int i = 1/0;

            //2.6.更新转入账户
            accountDao.updateAccount(target);

            //3.提交事务
            txManager.commit();
            //4.返回结果
        } catch (Exception e) {
            System.out.println("bb");
            //5.回滚事务
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            System.out.println("cc");
            //6.释放连接
            txManager.release();
        }
    }

}













