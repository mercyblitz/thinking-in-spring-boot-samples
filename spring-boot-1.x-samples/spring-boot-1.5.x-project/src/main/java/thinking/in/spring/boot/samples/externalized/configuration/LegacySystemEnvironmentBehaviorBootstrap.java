package thinking.in.spring.boot.samples.externalized.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ExternalizedConfiguration 章节：检验遗留 Spring Boot 应用系统环境变量
 * {@link ConfigurationProperties} 属性绑定行为
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@SpringBootApplication
public class LegacySystemEnvironmentBehaviorBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        LegacySystemEnvironmentBehaviorBootstrap.class, User.class)
                        .web(false) // 非 Web 应用
                        .properties("user.id = 1") // 设置默认属性（最低外部化配置优先级）
                        .run(args);
        User user = context.getBean(User.class);
        System.out.println("user 用户对象 : " + user);
        context.close();
    }

    @ConfigurationProperties(prefix = "user")
    public static class User {

        private Long id;

        public Long getId() { return id; }

        public void setId(Long id) { this.id = id; }

        @Override
        public String toString() {
            return "User{" + "id=" + id + '}';
        }
    }
}