package thinking.in.spring.boot.samples.spring3.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring Web MVC 配置
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "thinking.in.spring.boot.samples.spring3.web.controller")
public class SpringWebMvcConfiguration {
}
