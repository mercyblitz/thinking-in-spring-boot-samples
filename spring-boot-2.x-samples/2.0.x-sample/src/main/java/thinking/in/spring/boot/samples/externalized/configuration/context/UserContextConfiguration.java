package thinking.in.spring.boot.samples.externalized.configuration.context;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import thinking.in.spring.boot.samples.api.domain.User;

import java.time.LocalDate;

/**
 * 用户上下文{@link Configuration 配置}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 2.0.0
 */
@Configuration
public class UserContextConfiguration {
    // @Value 字段注入方式
    @Value("${user.id}") Long id;

    private Environment environment = null;

    public UserContextConfiguration(BeanFactory beanFactory) {
        String beanName = ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;
        this.environment = beanFactory.getBean(beanName, Environment.class);
        // 获取 ID 为 conversionService 的 ConversionService Bean，实际为 DefaultFormattingConversionService
        ConfigurableConversionService conversionService =
                beanFactory.getBean("conversionService", ConfigurableConversionService.class);
        // 设置 ConversionService Bean
        ((ConfigurableEnvironment) environment).setConversionService(conversionService);
    }

    @Bean("user") // User Bean Name
    public User user(@Value("${user.id}") Long id,
                     @Value("${name:小马哥}") String name,
                     @Value("${user.birthday:1985-12-11}") LocalDate birthday
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setBirthday(birthday);
        System.out.println("user() : @Value 注入成功!");
        return user;
    }

    @Bean // 不指定 Bean Name , 使用方法名称 "user2" 作为 Bean Name
    public User user2() {
        User user = new User();
        // "user.id" 必须存在，否则抛出 IllegalStateException 异常
        Long id = environment.getRequiredProperty("user.id", Long.class);
        // 当 "name" 不存在时，使用字符串 "小马哥" 作为默认值
        String name = environment.getProperty("name", "小马哥");
        // 获取 "user.birthday" 属性，并且转换成 LocalDate 对象
        LocalDate birthday = environment.getProperty("user.birthday", LocalDate.class, LocalDate.parse("1985-12-11"));
        user.setId(id);
        user.setName(name);
        user.setBirthday(birthday);
        System.out.println("user() : Environment 读取成功!");
        return user;

    }

    /**
     * 通过外部化配置属性 "user.name" 调整 "user" Bean 的名称
     *
     * @param user "user" Bean 对象
     */
    @Autowired
    @Qualifier("user")
    public void changeUserName(User user) { // 依赖注入 Bean Name 为 "user" 的对象
        String oldName = user.getName();
        String name = environment.getProperty("user.name");
        user.setName(name);
        System.out.printf("'user' Bean 曾用名: %s , 改后名 : %s \n", oldName, name);
    }

}