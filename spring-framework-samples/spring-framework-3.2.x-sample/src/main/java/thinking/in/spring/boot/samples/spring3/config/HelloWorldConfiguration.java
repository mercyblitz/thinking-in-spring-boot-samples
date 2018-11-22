package thinking.in.spring.boot.samples.spring3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld 配置 Bean，被{@link Configuration} 标注
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() { // 创建名为"helloWorld" String 类型的Bean
        return "Hello,World";
    }
}
