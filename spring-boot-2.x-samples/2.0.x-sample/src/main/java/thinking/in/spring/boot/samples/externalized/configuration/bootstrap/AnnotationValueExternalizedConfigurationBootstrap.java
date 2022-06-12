package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.format.support.DefaultFormattingConversionService;
import thinking.in.spring.boot.samples.externalized.configuration.context.UserContextConfiguration;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateConverter;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateGenericConverter;
import thinking.in.spring.boot.samples.api.domain.User;

/**
 * Annotation {@link Value} 外部化配置 Spring Boot 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Import(UserContextConfiguration.class)
@EnableAutoConfiguration
//@ImportResource(locations = "classpath:/META-INF/spring/custom-editor.xml")
public class AnnotationValueExternalizedConfigurationBootstrap {

    @Bean// 按照官方文档约定，使用方法名称方式指定 Bean ID 为 conversionService
    public DefaultFormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        // 新建 StringToLocalDateConverter 作为代理对象
        StringToLocalDateConverter delegate = new StringToLocalDateConverter();
        // 注册 StringToLocalDateGenericConverter
        conversionService.addConverter(new StringToLocalDateGenericConverter(delegate));
        return conversionService;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        AnnotationValueExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        User user = context.getBean("user", User.class);

        User user2 = context.getBean("user2", User.class);

        System.out.println("user 用户对象 : " + user);

        System.out.println("user2 用户对象 : " + user2);

        // 关闭上下文
        context.close();
    }
}
