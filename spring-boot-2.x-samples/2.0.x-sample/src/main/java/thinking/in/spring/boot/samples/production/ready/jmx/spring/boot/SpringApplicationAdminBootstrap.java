package thinking.in.spring.boot.samples.production.ready.jmx.spring.boot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.admin.SpringApplicationAdminMXBean;
import org.springframework.boot.admin.SpringApplicationAdminMXBeanRegistrar;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * {@link SpringApplicationAdminMXBeanRegistrar.SpringApplicationAdmin} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see SpringApplicationAdminMXBean
 * @see SpringApplicationAdminMXBeanRegistrar.SpringApplicationAdmin
 * @see SpringApplicationAdminJmxAutoConfiguration
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class SpringApplicationAdminBootstrap {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationAdminBootstrap.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .properties("spring.application.admin.enabled=true") // 激活 SpringApplicationAdminJmxAutoConfiguration
                .run(args);
        System.out.println("按任意键结束...");
        System.in.read();
        context.close();
    }
}
