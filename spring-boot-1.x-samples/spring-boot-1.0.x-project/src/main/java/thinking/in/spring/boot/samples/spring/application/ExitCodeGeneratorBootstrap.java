package thinking.in.spring.boot.samples.spring.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * {@link ExitCodeGenerator 错误码生成器} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ExitCodeGenerator
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class ExitCodeGeneratorBootstrap {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        System.out.println("ExitCodeGenerator Bean 创建...");
        return () -> {
            System.out.println("执行退出码(88)生成...");
            return 88;
        };
    }

    public static void main(String[] args) {
//        重构前的实现
//        new SpringApplicationBuilder(ExitCodeGeneratorBootstrap.class)
//                .web(false) // 非 Web 应用
//                .run(args)  // 运行 SpringBoot 应用
//                .close();   // 关闭应用上下文

//        重构后的实现
        int exitCode = SpringApplication.exit(new SpringApplicationBuilder(ExitCodeGeneratorBootstrap.class)
                .web(false) // 非 Web 应用
                .run(args)  // 运行 SpringBoot 应用
        );
        // 传递退出码到 System#exit(int) 方法
        System.exit(exitCode);
    }
}
