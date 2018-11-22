package thinking.in.spring.boot.samples.spring.application.event;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.event.ContextRefreshedEvent;
import thinking.in.spring.boot.samples.spring.application.event.listener.ContextRefreshedEventListener;

/**
 * Spring Boot {@link ContextRefreshedEventListener} 重复监听引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ContextRefreshedEvent
 * @see ContextRefreshedEventListener
 * @since 1.0.0
 */
public class DuplicatedEventListenerBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DuplicatedEventListenerBootstrap.class)
                .web(false)            // 非 Web 应用
                .run(args)             // 运行 SpringApplication
                .close();              // 关闭 ConfigurableApplicationContext
    }
}
