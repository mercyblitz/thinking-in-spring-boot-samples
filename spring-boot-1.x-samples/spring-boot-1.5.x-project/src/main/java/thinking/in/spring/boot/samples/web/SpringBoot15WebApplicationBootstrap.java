package thinking.in.spring.boot.samples.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.boot.autoconfigure.security.SecurityProperties.BASIC_AUTH_ORDER;

/**
 * Spring Boot 1.5.x Web 应用引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@SpringBootApplication
@Order(BASIC_AUTH_ORDER + 1)
@Controller
public class SpringBoot15WebApplicationBootstrap extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot15WebApplicationBootstrap.class);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
    }

}
