import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

/**
 * {@link ComponentScan @ComponentScan} 默认包引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@ComponentScan(basePackageClasses = DefaultPackageBootstrap.class)
public class DefaultPackageBootstrap {

    public static void main(String[] args) {
        // 注册当前引导类作为配置 Class，并启动当前上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DefaultPackageBootstrap.class);
        // 输出当前 Spring 应用上下文中所有注册的 Bean 名称
        System.out.println("当前 Spring 应用上下文中所有注册的 Bean 名称：");
        Stream.of(context.getBeanDefinitionNames())
                .map(name -> "\t" + name) // 增加格式缩进
                .forEach(System.out::println);
        // 关闭上下文
        context.close();
    }
}
