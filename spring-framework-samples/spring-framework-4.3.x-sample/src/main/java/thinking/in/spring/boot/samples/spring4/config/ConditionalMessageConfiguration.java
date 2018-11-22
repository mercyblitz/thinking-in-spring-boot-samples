package thinking.in.spring.boot.samples.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.boot.samples.spring4.annotation.ConditionalOnSystemProperty;

/**
 * 条件消息配置
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class ConditionalMessageConfiguration {

    @ConditionalOnSystemProperty(name = "language", value = "Chinese")
    @Bean("message") // Bean 名称 "message" 的中文消息
    public String chineseMessage() {
        return "你好，世界";
    }

    @ConditionalOnSystemProperty(name = "language", value = "English")
    @Bean("message") // Bean 名称 "message" 的英文消息
    public String englishMessage() {
        return "Hello,World";
    }
}
