package thinking.in.spring.boot.samples.production.ready.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link ObjectFactory} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ObjectFactory
 * @since 1.0.0
 */
public class ObjectFactoryBootstrap {

    @Bean
    public String helloWorld() {
        return "Hello,World";
    }

    @Autowired
    private String helloWorld;

    @Autowired
    private ObjectFactory<String> objectFactory;

    public static void main(String[] args) {
        // 注册 ObjectFactoryBootstrap 并启动应用上下文
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ObjectFactoryBootstrap.class);
        // 获取 ObjectFactoryBootstrap Bean
        ObjectFactoryBootstrap bootstrap = context.getBean(ObjectFactoryBootstrap.class);
        // 输出依赖注入内容
        System.out.printf("Bean 注入 helloWorld 内容为： %s\n", bootstrap.helloWorld);
        // 输出 ObjectFactory.getObject() 内容
        ObjectFactory<String> objectFactory = bootstrap.objectFactory;
        System.out.printf("ObjectFactory[类型：%s] 注入 getObject() 内容为：%s\n",
                objectFactory.getClass(), objectFactory.getObject());
        // 比较两者是否相同
        System.out.printf("Bean helloWorld 与 objectFactory.getObject() 的内容是否相同：%s\n",
                bootstrap.helloWorld == objectFactory.getObject());
        // 关闭应用上下文
        context.close();
    }
}