package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateConverter;
import thinking.in.spring.boot.samples.externalized.configuration.convert.support.StringToLocalDateGenericConverter;

import java.time.LocalDate;

/**
 * 检验 {@link Converter} Bean Annotation {@link Value} 外部化配置 Spring Boot 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class ConverterBeanForAnnotationValueBootstrap {

    @Bean
    public LocalDate birthday(@Value("${user.birthday}") LocalDate birthday) {
        return birthday;
    }

    @Bean
    public StringToLocalDateConverter stringToLocalDateConverter() {
        return new StringToLocalDateConverter();
    }

    @Bean
    public GenericConverter stringToLocalDateGenericConverter(StringToLocalDateConverter converter) {
        return new StringToLocalDateGenericConverter(converter);
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        ConverterBeanForAnnotationValueBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        LocalDate birthday = context.getBean("birthday", LocalDate.class);
        System.out.println("(@Value(\"${user.birthday}\") : " + birthday);
        // 关闭上下文
        context.close();
    }
}
