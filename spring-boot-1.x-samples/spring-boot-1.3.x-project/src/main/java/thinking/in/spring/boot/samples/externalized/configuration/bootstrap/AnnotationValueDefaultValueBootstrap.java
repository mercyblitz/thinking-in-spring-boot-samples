package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link Value} 默认值 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class AnnotationValueDefaultValueBootstrap {

    @Value("${userName:小马哥}")
    private String userName;


    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(AnnotationValueDefaultValueBootstrap.class)
                        .web(false)
                        .run(args);

        AnnotationValueDefaultValueBootstrap bootstrap =
                context.getBean(AnnotationValueDefaultValueBootstrap.class);

        System.out.println("userName : " + bootstrap.userName);

        // 关闭上下文
        context.close();
    }
}
