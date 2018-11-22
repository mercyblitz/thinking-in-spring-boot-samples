package thinking.in.spring.boot.samples.production.ready.actuator.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring Security 安全提升的 HTTP Endpoints 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EndpointRequest
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class SpringSecurityHttpEndpointsBootstrap extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests().anyRequest().hasRole("ENDPOINT_ADMIN")
                .and()
                .httpBasic();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 为用户 mercyblitz 分配密码和 ENDPOINT_ADMIN 权限
        manager.createUser(users.username("mercyblitz").password("123456").roles("ENDPOINT_ADMIN").build());
        return manager;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringSecurityHttpEndpointsBootstrap.class)
                .properties("management.endpoints.web.exposure.include=*") // 开放所有 Endpoints
                .run(args);
    }
}
