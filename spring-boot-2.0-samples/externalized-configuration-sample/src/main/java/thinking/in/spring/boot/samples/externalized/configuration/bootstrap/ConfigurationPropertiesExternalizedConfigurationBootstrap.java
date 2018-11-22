package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateConverter;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateGenericConverter;
import thinking.in.spring.boot.samples.api.domain.User;
import thinking.in.spring.boot.samples.externalized.configuration.validation.UserCityNameNotEmptyValidator;

/**
 * {@link ConfigurationProperties} 外部化配置 Spring Boot 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableConfigurationProperties
@SpringBootConfiguration
@ImportResource(locations = "classpath:/META-INF/spring/custom-editor.xml")
public class ConfigurationPropertiesExternalizedConfigurationBootstrap {

    @Bean
    @Validated
    @ConfigurationProperties(value = "user", ignoreUnknownFields = false)
    @ConditionalOnProperty(prefix = "user", name = "HOME-PAGE", havingValue = "https://weibo.com/mercyblitz")
    public User user() {
        return new User();
    }

    @Bean(name = ConfigurationPropertiesBindingPostProcessor.VALIDATOR_BEAN_NAME)
    public Validator userCityNameNotEmptyValidator() {
        return new UserCityNameNotEmptyValidator();
    }

//    @Bean // 按照官方文档约定，使用方法名称方式指定 Bean ID 为 conversionService
//    public DefaultFormattingConversionService conversionService() {
//        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
//        // 注册 StringToLocalDateConverter
//        conversionService.addConverter(new StringToLocalDateConverter());
//        return conversionService;
//    }

//    @Bean
//    @ConfigurationPropertiesBinding
//    public Converter converter() {
//        return new StringToLocalDateConverter();
//    }

    @Bean
    @ConfigurationPropertiesBinding
    public GenericConverter stringToLocalDateGenericConverter() {
        return new StringToLocalDateGenericConverter(new StringToLocalDateConverter());
    }


    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        ConfigurationPropertiesExternalizedConfigurationBootstrap.class)
                        .properties("user.city.postCode=0731")
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        User user = context.getBean(User.class);

        System.out.println("user 用户对象 : " + user);

        // 关闭上下文
        context.close();
    }

}
