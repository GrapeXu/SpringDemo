package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 * 公共配置
 */
@Configuration//new AnnotationConfigApplicationContext(SpringConfiguration.class);可省,但不建议
//@ComponentScan(basePackages = "com.learn")//JdbcConfig没扫到
//@ComponentScan(basePackages = {"com.learn", "config"})//扫俩,可用,主从配置,那我不想扫俩？:@import
@ComponentScan(basePackages = "com.learn")//可省 new XXXContext(SpringConfiguration.class)
@Import(JdbcConfig.class)//导入其他配置类,数组,还需要JdbcConfig注册吗
public class SpringConfiguration {

}





















