import com.config.SpringConfiguration;
import com.learn.domain.Account;
import com.learn.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试,测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)//创建容器
@ContextConfiguration(classes = SpringConfiguration.class)//xml还是注解方式
//@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    AccountService service;

    @Test
    public void testFindAll() {
        //3.执行方法
        List<Account> accounts = service.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //3.执行方法
        Account account = service.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        //3.执行方法
        Account account = new Account(null, "呵呵", 1000F);
        service.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        //3.执行方法
        Account account = service.findAccountById(3);
        account.setMoney(10000F);
        service.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //3.执行方法
        service.deleteAccountById(4);
    }


}



















