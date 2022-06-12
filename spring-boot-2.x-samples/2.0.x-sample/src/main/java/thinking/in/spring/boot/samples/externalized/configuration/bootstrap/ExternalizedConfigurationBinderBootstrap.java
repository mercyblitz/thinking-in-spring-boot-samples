package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.format.support.DefaultFormattingConversionService;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateConverter;
import thinking.in.spring.boot.samples.api.domain.User;

import java.io.IOException;
import java.util.Properties;

/**
 * 外部化配置 {@link Binder} 引导类，理解 {@link ConfigurationPropertyName}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ExternalizedConfigurationBinderBootstrap {

    public static void main(String[] args) throws IOException {
        // application.properties 文件资源 classpath 路径
        String location = "application.properties";
        // 编码化的 Resource 对象（解决乱码问题）
        EncodedResource resource = new EncodedResource(new ClassPathResource(location), "UTF-8");
        // 加载 application.properties 文件，转化为 Properties 对象
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        // 创建 Properties 类型的 PropertySource
        PropertiesPropertySource propertySource = new PropertiesPropertySource("map", properties);
        // 转化为 Spring Boot 2 外部化配置源 ConfigurationPropertySource 集合
        Iterable<ConfigurationPropertySource> propertySources = ConfigurationPropertySources.from(propertySource);
        // 创建 Spring Boot 2 Binder 对象（设置 ConversionService ，扩展类型转换能力）
        Binder binder = new Binder(propertySources, null, conversionService());
        // 执行绑定，返回绑定结果
        BindResult<User> bindResult = binder.bind("user", User.class);
        // 获取绑定对象
        User user = bindResult.get();
        // 输出结果
        System.out.println(user);

    }

    /**
     * 创建 {@link ConversionService}
     *
     * @return {@link ConversionService} 对象
     */
    private static ConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        // 注册 StringToLocalDateConverter
        conversionService.addConverter(new StringToLocalDateConverter());
        return conversionService;
    }

}
