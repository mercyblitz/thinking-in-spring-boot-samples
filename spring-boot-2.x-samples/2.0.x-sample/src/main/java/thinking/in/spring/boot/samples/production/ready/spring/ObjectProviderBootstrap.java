package thinking.in.spring.boot.samples.production.ready.spring;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thinking.in.spring.boot.samples.production.ready.jmx.User;

/**
 * {@link ObjectProvider} 注入引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ObjectProvider
 * @since 1.0.0
 */
public class ObjectProviderBootstrap {

    @Autowired
    private ObjectProvider<String> helloWorldObjectProvider;

    @Autowired(required = false) // ObjectProvider#getIfAvailable() 等价
    private String helloWorld;

    public static void main(String[] args) {
        // 注册 ObjectProviderBootstrap 并 启动应用上下文
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ObjectProviderBootstrap.class);
        // 获取 ObjectProviderBootstrap Bean
        ObjectProviderBootstrap bootstrap = context.getBean(ObjectProviderBootstrap.class);
        // 输出依赖注入内容
        System.out.printf("Bean 注入 helloWorld 内容为： %s\n", bootstrap.helloWorld);
        // 输出 ObjectProvider.getIfAvailable() 内容
        String helloWorld = bootstrap.helloWorldObjectProvider.getIfAvailable();
        System.out.printf("ObjectProvider[类型：%s] 注入 getIfAvailable() 内容为：%s\n",
                bootstrap.helloWorldObjectProvider.getClass(), helloWorld);
        // 关闭应用上下文
        context.close();
    }
}
