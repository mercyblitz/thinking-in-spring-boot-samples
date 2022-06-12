package thinking.in.spring.boot.samples.production.ready.actuator.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * 管理端 {@link ApplicationContext 应用上下文} Endpoints 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration(
        exclude = ReactiveSecurityAutoConfiguration.class // 排除 Spring Security 自动装配
)
@RestController
public class ManagementContextEndpointsBootstrap {

    @Autowired
    private ApplicationContext applicationContext; // 当前服务端应用上下文

    @PostConstruct
    public void init() {
        System.out.printf("当前服务端应用上下文 ID：%s\n", applicationContext.getId());
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ManagementContextEndpointsBootstrap.class)
                .web(WebApplicationType.REACTIVE) // Reactive Web 应用
                .properties("management.endpoints.web.exposure.include=beans") // 仅暴露 beans
//                .properties("management.server.port=8081") // 配置管理端 Endpoints 端口
                .run(args);
    }
}
