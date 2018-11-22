package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link Binder} 示例引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class BinderBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(BinderBootstrap.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .properties("user.city.postCode=0731")
                .run(args);
        ConfigurableEnvironment environment = context.getEnvironment();
        // 从 Environment 中获取 ConfigurationPropertySource 集合
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        // 构造 Binder 对象，并使用 ConfigurationPropertySource 集合作为配置源
        Binder binder = new Binder(sources);
        // 构造 ConfigurationPropertyName（Spring Boot 2.0 API）
        ConfigurationPropertyName propertyName = ConfigurationPropertyName.of("user.city.post-code");
        // 构造 Bindable 对象，包装 postCode
        Bindable<String> postCodeBindable = Bindable.of(String.class);
        BindResult<String> result = binder.bind(propertyName, postCodeBindable);
        String postCode = result.get();
        System.out.println("postCode = " + postCode);
        // 关闭上下文
        context.close();
    }
}
