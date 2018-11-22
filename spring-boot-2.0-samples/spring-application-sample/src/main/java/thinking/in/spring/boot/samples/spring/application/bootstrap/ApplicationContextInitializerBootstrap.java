package thinking.in.spring.boot.samples.spring.application.bootstrap;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import thinking.in.spring.boot.samples.spring.application.context.HelloWorldApplicationContextInitializer;

/**
 * 演示 {@link ApplicationContextInitializer} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see HelloWorldApplicationContextInitializer
 * @since 1.0.0
 */
public class ApplicationContextInitializerBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Object.class)
                // 重复注册 HelloWorldApplicationContextInitializer
                .initializers(new HelloWorldApplicationContextInitializer(),
                        new HelloWorldApplicationContextInitializer())
                .run(args) // 运行 SpringApplication
                .close();  // 关闭 Spring 应用上下文
    }
}
