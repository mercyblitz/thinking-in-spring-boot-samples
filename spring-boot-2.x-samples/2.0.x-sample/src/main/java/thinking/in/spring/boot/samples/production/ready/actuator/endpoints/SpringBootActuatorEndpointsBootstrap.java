/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package thinking.in.spring.boot.samples.production.ready.actuator.endpoints;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Spring Boot Actuator Endpoints 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class SpringBootActuatorEndpointsBootstrap {

    /**
     * 由于当前工程依赖 org.springframework.security:spring-security-web 的缘故，
     * BASIC 验证需要显示地关闭
     *
     * @param http {@link ServerHttpSecurity}
     * @return {@link SecurityWebFilterChain}
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.securityMatcher(EndpointRequest.toAnyEndpoint())
                .httpBasic().disable() // 关闭 BASIC 验证
                .build();
    }

    @Bean
    @Lazy
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 将 Bean Scope 调整为原型（prototype）
    public ApplicationRunner applicationRunner(ApplicationContext context) {
        return args -> {
            System.out.printf("当前服务端应用上下文 ID 为：%s\n", context.getId());
        };
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootActuatorEndpointsBootstrap.class)
                .properties("management.endpoints.web.exposure.include=beans") // 开放 beans Endpoint
                .properties("management.server.port=8081")                     // 配置管理端不同的端口，使其应用上下文独立
                .parent(parentContext())                                       // 关联双亲应用上下文
                .run(args);
    }

    private static ConfigurableApplicationContext parentContext() {
        GenericApplicationContext parentContext = new GenericApplicationContext();
        parentContext.setId("parent-application-context"); // 设置上下文 ID
        // 注册名为 "helloWorld" 的 String 类型的 Bean，内容为 "Hello,World!"
        parentContext.registerBean("helloWorld", String.class, () -> "Hello,World!");
        parentContext.refresh(); // 双亲应用上下文务必启动
        return parentContext;
    }
}

