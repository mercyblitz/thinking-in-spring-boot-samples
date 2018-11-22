package thinking.in.spring.boot.samples.spring3.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Role;
import thinking.in.spring.boot.samples.spring3.domain.User;

/**
 * Spring XML 配置文件（classpath:/META-INF/spring/context.xml）的替代 Java 配置
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@ImportResource("classpath:/META-INF/spring/others.xml")         // 替代<import>
@Configuration("springContextConfiguration")
@ComponentScan(basePackages = "thinking.in.spring.boot.samples") // 替代<context:component-scan>
@Profile("!production") // 非生产环境
public class SpringContextConfiguration {

    @Lazy
    @Primary
    @DependsOn("springContextConfiguration") // 依赖 "springContextConfiguration"
    @Bean(name = "user") // Bean 名称为 "user"
    @Role(BeanDefinition.ROLE_APPLICATION) // 为应用
    public User user() {
        User user = new User();
        user.setName("小马哥"); // 设置 property
        return user;
    }
}