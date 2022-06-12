package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.source.ConfigurationProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link ConfigurationPropertySources} 示例 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class ConfigurationPropertySourcesBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        ConfigurationPropertySourcesBootstrap.class)
                        .properties("user.city.postCode=0571")
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        ConfigurableEnvironment environment = context.getEnvironment();
        // 从 Environment 中获取 ConfigurationPropertySource 集合
        Iterable<ConfigurationPropertySource> configurationPropertySources = ConfigurationPropertySources.get(environment);
        // 构造 ConfigurationPropertyName（Spring Boot 2.0 API）
        ConfigurationPropertyName propertyName = ConfigurationPropertyName.of("user.city.post-code");
        // 迭代 ConfigurationPropertySource
        configurationPropertySources.forEach(configurationPropertySource -> {
            // 通过 ConfigurationPropertyName 获取 ConfigurationProperty
            ConfigurationProperty configurationProperty = configurationPropertySource.getConfigurationProperty(propertyName);
            if (configurationProperty != null) {
                String postCode = (String) configurationProperty.getValue();
                System.out.println("postCode = " + postCode + " , from : " + configurationProperty.getOrigin());
            }
        });
        // 关闭上下文
        context.close();
    }
}
