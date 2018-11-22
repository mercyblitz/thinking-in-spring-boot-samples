package thinking.in.spring.boot.samples.diagnostics;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * {@link UnknownError 未知错误} Spring Boot 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see UnknownError
 * @since 1.0.0
 */
public class UnknownErrorSpringBootBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Object.class)
                .initializers(context -> {
                    throw new UnknownError("故意抛出异常");
                })
                .web(false) // 非 Web 应用
                .run(args)  // 运行 SpringApplication
                .close();   // 关闭 Spring 应用上下文
    }
}
