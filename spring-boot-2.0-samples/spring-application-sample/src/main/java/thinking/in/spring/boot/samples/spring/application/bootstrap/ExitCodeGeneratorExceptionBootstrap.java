package thinking.in.spring.boot.samples.spring.application.bootstrap;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * {@link ExitCodeGenerator} {@link Exception 异常}引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ExitCodeGenerator
 * @see Throwable
 * @since 1.0.0
 */
public class ExitCodeGeneratorExceptionBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Object.class)
                .listeners(
//                        event -> {   // 取消所有 Spring Boot 事件监听
                        (ApplicationListener<ApplicationReadyEvent>) event -> {
                            throw new ExitCodeGeneratorThrowable(event.getClass().getSimpleName());
                        })
                .web(false)            // 非 Web 应用
                .run(args)             // 运行 SpringApplication
                .close();              // 关闭 ConfigurableApplicationContext
    }

    static class ExitCodeGeneratorThrowable extends RuntimeException implements ExitCodeGenerator {

        public ExitCodeGeneratorThrowable(String message) {
            super(message);
        }

        @Override
        public int getExitCode() {
            return 95;
        }
    }
}
