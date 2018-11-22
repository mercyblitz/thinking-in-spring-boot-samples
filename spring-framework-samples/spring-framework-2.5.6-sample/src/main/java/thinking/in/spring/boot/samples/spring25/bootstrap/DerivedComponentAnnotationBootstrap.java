package thinking.in.spring.boot.samples.spring25.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import thinking.in.spring.boot.samples.spring25.repository.NameRepository;

/**
 * {@link Component} "派生"注解 引导程序
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class DerivedComponentAnnotationBootstrap {

    static {
        // 解决 Spring 2.5.x 不兼容 Java 8 的问题
        // 同时，请注意 Java Security 策略，必须具备 PropertyPermission
        System.setProperty("java.version", "1.7.0");
    }

    public static void main(String[] args) {
        // 构建 XML 配置驱动 Spring 上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        // 设置 XML 配置文件的位置
        context.setConfigLocation("classpath:/META-INF/spring/context.xml");
        // 启动上下文
        context.refresh();
        // 获取名称为 "chineseNameRepository" Bean 对象
        NameRepository nameRepository = (NameRepository) context.getBean("chineseNameRepository");
        // 输出用户名称：[张三, 李四, 小马哥]
        System.out.printf("nameRepository.findAll() = %s \n", nameRepository.findAll());
    }
}
