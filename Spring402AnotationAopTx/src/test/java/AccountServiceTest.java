import com.learn.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用Junit单元测试,测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired//没手动context.getBean, 直接省事了
    private AccountService as;

    /**
     * 这里AOP顺序是有问题的
     */
    @Test
    public void testTransfer() {
        as.transfer("李四", "王五", 1f);
    }

}



















