package thinking.in.spring.boot.samples.spring.application.event;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring 事件监听器引导类
 * 来自于 Spring Boot META-INF/spring.factories 的 {@link ApplicationListener Spring 事件监听器} 监听 Spring 事件
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ApplicationEvent
 * @see ApplicationListener
 * @see EventPublishingRunListener#contextLoaded(ConfigurableApplicationContext)
 * @since 1.0.0
 */
public class SpringEventListenerBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Object.class) // 非 @Configuration 充当配置源
                .listeners(event ->                // 添加 ApplicationListener
                        System.out.printf("监听到事件: %s , 事件源 : %s\n"
                                , event.getClass().getSimpleName(), event.getSource())
                )
                .web(false)                        // 非 Web 应用
                .run(args)                         // 运行 SpringApplication
                .close();                          // 关闭 ConfigurableApplicationContext
    }
}
