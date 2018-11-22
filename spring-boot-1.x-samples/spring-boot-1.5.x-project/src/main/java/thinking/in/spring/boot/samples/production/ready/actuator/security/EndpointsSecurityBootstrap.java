package thinking.in.spring.boot.samples.production.ready.actuator.security;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Actuator {@link Endpoint Endpoints} 安全引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Endpoint
 * @since 1.0.0
 */
@RestController
@EnableAutoConfiguration
public class EndpointsSecurityBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EndpointsSecurityBootstrap.class)
                .properties("management.context-path = /man")
                .run(args);
    }

    @GetMapping("/man/hello")
    public String hello() {
        return "Hello";
    }
}
