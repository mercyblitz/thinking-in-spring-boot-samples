package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import thinking.in.spring.boot.samples.externalized.configuration.context.UserContextConfiguration;
import thinking.in.spring.boot.samples.api.domain.User;

/**
 * {@link Environment} 外部化配置 Spring Boot 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Import(UserContextConfiguration.class)
@EnableAutoConfiguration
public class EnvironmentExternalizedConfigurationBootstrap {

    @Bean(name = "primaryEnvironment")
    @Primary
    public Environment environment() {
        return new StandardEnvironment();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        EnvironmentExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        User user = context.getBean("user", User.class);

        User user2 = context.getBean("user2", User.class);

        System.out.println("user 用户对象 : " + user);

        System.out.println("user2 用户对象 : " + user2);

        // 关闭上下文
        context.close();
    }

}
