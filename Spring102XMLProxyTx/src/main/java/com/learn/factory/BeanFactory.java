package com.learn.factory;

import com.learn.domain.Account;
import com.learn.service.AccountService;
import com.learn.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {

    private AccountService accountService;

    /**
     * 用于注入
     * ?为啥加final
     * @param accountService
     */
    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private TransactionManager txManager;

    /**
     * 需要Spring提供注入,我们提供set方法,这里是xml配置
     * 注解配置没set也行
     * @param txManager
     */
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 本来 获取Service的直接return
     * 现在 获取Service 代理对象
     * 工厂模式 用该方法提供service, service的方法 被 增强了 ，包上了厚厚的一层。
     * @return
     */
    public AccountService getAccountService() {
        return (AccountService)Proxy.newProxyInstance(
                accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务支持
                     * invoke方法具有拦截功能,能拦截 被代理对象 中的所有执行的方法
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //消除重复代码 确实，事务代码一遍完事 全在这里做
                        //TransactionManager和Service的耦合降低,Tx方法该，这里改1次，Service改n多次
                        //多个代码片段公有部分的提取
                        Object returnVal = null;
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            //List<Account> accounts = accountDao.findAllAccount();
                            returnVal = method.invoke(accountService, args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return returnVal;
                        } catch (Exception e) {
                            //5.回滚事务
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放连接
                            txManager.release();
                        }
                    }
                }
        );
    }
}
