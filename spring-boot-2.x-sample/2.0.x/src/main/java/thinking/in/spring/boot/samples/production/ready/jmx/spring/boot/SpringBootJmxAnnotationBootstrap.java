package thinking.in.spring.boot.samples.production.ready.jmx.spring.boot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.boot.samples.production.ready.jmx.spring.Calculator;

import javax.management.modelmbean.ModelMBean;
import java.io.IOException;

/**
 * Spring Boot JMX 注解驱动引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see JmxAutoConfiguration
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class SpringBootJmxAnnotationBootstrap {

    /**
     * 暴露 {@link Calculator} Bean 为 JMX {@link ModelMBean}
     *
     * @return {@link Calculator} Bean
     */
    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringBootJmxAnnotationBootstrap.class)
                .web(WebApplicationType.NONE)  // 非 Web 应用
                .run(args);
        System.out.println("按任意键结束...");
        System.in.read();
        context.close();
    }
}
