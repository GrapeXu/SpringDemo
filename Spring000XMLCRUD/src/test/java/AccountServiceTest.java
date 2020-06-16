import com.learn.dao.impl.AccountDaoImpl;
import com.learn.domain.Account;
import com.learn.service.AccountService;
import com.learn.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试,测试我们的配置
 */
public class AccountServiceTest {

    @Test
    public void testFindAll() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService service = context.getBean("accountService", AccountService.class);
        //3.执行方法
        List<Account> accounts = service.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService service = context.getBean("accountService", AccountService.class);
        //3.执行方法
        Account account = service.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService service = context.getBean("accountService", AccountService.class);
        //3.执行方法
        Account account = new Account(null, "呵呵", 1000F);
        service.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService service = context.getBean("accountService", AccountService.class);
        //3.执行方法
        Account account = service.findAccountById(3);
        account.setMoney(10000F);
        service.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService service = context.getBean("accountService", AccountService.class);
        //3.执行方法
        service.deleteAccount(4);
    }

    @Test
    public void testSingleProto() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService service = context.getBean("accountService", AccountService.class);


        System.out.println(service.findAccountById(1));
        System.out.println(service.findAccountById(1));
        System.out.println(service.findAccountById(1));
        System.out.println(service.findAccountById(1));
    }
}



















