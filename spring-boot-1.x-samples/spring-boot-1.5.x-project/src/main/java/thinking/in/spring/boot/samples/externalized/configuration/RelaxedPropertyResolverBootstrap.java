package thinking.in.spring.boot.samples.externalized.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertyResolver;

/**
 * {@link RelaxedPropertyResolver} 示例引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class RelaxedPropertyResolverBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        RelaxedPropertyResolverBootstrap.class)
                        .properties("user.city.postCode=0571")
                        .web(false) // 非 Web 应用
                        .run(args);
        // 获取 Environment，也是 PropertyResolver 对象
        PropertyResolver environment = context.getEnvironment();
        // 属性名称前缀
        String prefix = "user.city.";
        // 创建 RelaxedPropertyResolver 实例
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, prefix);
        // 获取松散化配置属性
        String postCode = propertyResolver.getProperty("post-code");
        System.out.println("postCode = " + postCode);
        // 关闭上下文
        context.close();
    }
}
