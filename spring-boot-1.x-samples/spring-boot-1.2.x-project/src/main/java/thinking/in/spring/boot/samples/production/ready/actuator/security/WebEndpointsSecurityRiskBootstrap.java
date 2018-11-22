package thinking.in.spring.boot.samples.production.ready.actuator.security;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

/**
 * Web Endpoints 安全风险引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class WebEndpointsSecurityRiskBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebEndpointsSecurityRiskBootstrap.class)
                .properties("management.port=8081") // 设置管理端 Web 端口
                .run(args);
    }

    @Component
    public static class HelloWorldEndpoint extends AbstractEndpoint<String> {

        public HelloWorldEndpoint() {
            super("helloworld", false, true);
        }

        @Override
        public String invoke() {
            return "Hello,World";
        }
    }
}
