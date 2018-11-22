package thinking.in.spring.boot.samples.production.ready.actuator.management;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.autoconfigure.ManagementContextResolver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

/**
 * {@link ManagementContextResolver} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ManagementContextResolver
 * @since 1.0.0
 */
@EnableAutoConfiguration
@Order // 默认最低优先级，等价于 @Order(Ordered.LOWEST_PRECEDENCE)
public class ManagementContextResolverBootstrap implements SmartInitializingSingleton {

    @Autowired
    private ManagementContextResolver managementContextResolver;

    @Autowired
    private ApplicationContext applicationContext; // 服务端应用上下文

    private ApplicationContext managementContext; // 管理端应用上下文

    @PostConstruct
    public void init() {
        System.out.printf("当前 %s Bean 执行 init() 方法\n", getClass().getSimpleName());
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.printf("当前 %s Bean 执行 afterSingletonsInstantiated() 方法\n", getClass().getSimpleName());
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshedEvent(ContextRefreshedEvent event) {
        ApplicationContext currentApplicationContext = event.getApplicationContext();
        if (currentApplicationContext.equals(applicationContext)) { // 当应用上下文来自于服务端时
            System.out.printf("当前应用上下文 ID :%s 执行 onContextRefreshedEvent() 方法\n",
                    currentApplicationContext.getId());
        }
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.printf("当前应用上下文 ID :%s 执行 ApplicationRunner#run() 方法\n",
                    applicationContext.getId());
            managementContext = managementContextResolver.getApplicationContext();
            ApplicationContext parentContext = managementContext.getParent();
            System.out.printf("当前服务端应用上下文 ID : %s\n", applicationContext.getId());
            System.out.printf("当前管理端应用上下文 ID : %s，其双亲应用上下文 ID：%s\n",
                    managementContext.getId(),
                    parentContext == null ? null : parentContext.getId());
        };
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ManagementContextResolverBootstrap.class)
                .properties("management.port=8081") // 设置管理端 Web 端口 8081
                .run(args) // 运行 SpringApplication
                .close();  // 关闭当前应用上下文
    }
}
