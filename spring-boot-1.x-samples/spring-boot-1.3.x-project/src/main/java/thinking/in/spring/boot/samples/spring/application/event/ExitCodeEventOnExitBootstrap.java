package thinking.in.spring.boot.samples.spring.application.event;

import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;


/**
 * {@link ExitCodeEvent 退出码事件} {@link SpringApplication#exit(ApplicationContext, ExitCodeGenerator...)
 * SpringApplication 在正常结束}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ExitCodeEvent
 * @since 1.0.0
 */
public class ExitCodeEventOnExitBootstrap {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> {
            System.out.println("执行退出码(9)生成...");
            return 9;
        };
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(
                new SpringApplicationBuilder(ExitCodeEventOnExitBootstrap.class)
                        .listeners((ApplicationListener<ExitCodeEvent>) event ->
                                System.out.println("监听到退出码：" + event.getExitCode())
                        )
                        .web(false) // 非 Web 应用
                        .run(args)  // 运行 SpringBoot 应用
        ));
    }
}
