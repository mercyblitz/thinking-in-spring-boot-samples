package thinking.in.spring.boot.samples.production.ready.actuator.security;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Spring Security 安全提升的 Web Reactive Endpoints 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EndpointRequest
 * @since 1.0.0
 */
@EnableAutoConfiguration
@EnableWebFluxSecurity
public class SpringSecurityWebReactiveEndpointsBootstrap {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeExchange()
                .anyExchange()
                .hasRole("ENDPOINT_ADMIN")
                .and().httpBasic()
                .and().build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("mercyblitz")
                .password("123456")
                .roles("ENDPOINT_ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringSecurityWebReactiveEndpointsBootstrap.class)
                .properties("management.endpoints.web.exposure.include=*") // 开放所有 Endpoints
                .run(args);
    }
}
