package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.boot.samples.api.domain.User;

/**
 * Spring XML 配置 占位符引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class SpringXmlConfigPlaceholderBootstrap {

    public static void main(String[] args) {

        String[] locations = {"META-INF/spring/placeholder.xml", "META-INF/spring/user-context.xml"};

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);

        User user = applicationContext.getBean("user", User.class);

        System.out.println("用户对象 : " + user);
        // 关闭上下文
        applicationContext.close();
    }
}
