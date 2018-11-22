package thinking.in.spring.boot.samples.spring.application;


import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * {@link ExitCodeExceptionMapper} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ExitCodeExceptionMapper
 * @since 1.0.0
 */
public class ExitCodeExceptionMapperBootstrap {

    @Bean
    public ExitCodeExceptionMapper exitCodeExceptionMapper() {
        return (throwable) -> 128;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ExitCodeExceptionMapperBootstrap.class)
                .listeners((ApplicationListener<ApplicationReadyEvent>) event -> {
                    throw new RuntimeException(event.getClass().getSimpleName());
                })
                .web(false)            // 非 Web 应用
                .run(args)             // 运行 SpringApplication
                .close();              // 关闭 ConfigurableApplicationContext
    }
}
