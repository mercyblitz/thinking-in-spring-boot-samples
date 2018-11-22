package thinking.in.spring.boot.samples.spring.application.event;

import org.springframework.boot.builder.SpringApplicationBuilder;
import thinking.in.spring.boot.samples.spring.application.event.listener.MultipleSpringBootEventsListener;

/**
 * {@link MultipleSpringBootEventsListener 多 Spring Boot 事件监听器}引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MultipleSpringBootEventsListener
 * @since 1.0.0
 */
public class MultipleSpringBootEventsListenerBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MultipleSpringBootEventsListener.class) // 注册为 Spring Bean
                .web(false)            // 非 Web 应用
                .run(args)             // 运行 SpringApplication
                .close();              // 关闭 ConfigurableApplicationContext
    }
}
