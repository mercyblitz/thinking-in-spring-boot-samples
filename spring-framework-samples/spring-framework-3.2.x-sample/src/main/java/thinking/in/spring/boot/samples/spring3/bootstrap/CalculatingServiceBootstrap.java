package thinking.in.spring.boot.samples.spring3.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import thinking.in.spring.boot.samples.spring3.service.CalculatingService;

/**
 * {@link CalculatingService} 引导程序
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackageClasses = CalculatingService.class)
public class CalculatingServiceBootstrap {

    static {
        // 通过 Java 系统属性设置 Spring Profile
        // 以下语句等效于 ConfigurableEnvironment.setActiveProfiles("Java8")
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "Java8");
        // 以下语句等效于 ConfigurableEnvironment.setDefaultProfiles("Java7")
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "Java7");
    }

    public static void main(String[] args) {
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 当前配置Bean 到 Spring 上下文
        context.register(CalculatingServiceBootstrap.class);
        // 启动上下文
        context.refresh();
        // 获取 CalculatingService Bean
        CalculatingService calculatingService = context.getBean(CalculatingService.class);
        // 输出累加结果
        calculatingService.sum(1, 2, 3, 4, 5);
        // 关闭上下文
        context.close();
    }
}
