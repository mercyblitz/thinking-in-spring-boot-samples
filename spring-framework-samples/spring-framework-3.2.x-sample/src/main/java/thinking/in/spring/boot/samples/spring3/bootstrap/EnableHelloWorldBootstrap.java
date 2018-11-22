package thinking.in.spring.boot.samples.spring3.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.boot.samples.spring3.annotation.EnableHelloWorld;

/**
 * {@link EnableHelloWorld} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@EnableHelloWorld
@Configuration
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        // 构建 Annotation 配置驱动 Spring 上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 当前引导类（被 @Configuration 标注） 到 Spring 上下文
        context.register(EnableHelloWorldBootstrap.class);
        // 启动上下文
        context.refresh();
        // 获取名称为 "helloWorld" Bean 对象
        String helloWorld = context.getBean("helloWorld", String.class);
        // 输出用户名称："Hello,World"
        System.out.printf("helloWorld = %s \n", helloWorld);
        // 关闭上下文
        context.close();
    }
}
