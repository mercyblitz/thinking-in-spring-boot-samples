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
package thinking.in.spring.boot.samples.externalized.configuration.bootstrap.web;

import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextEnvironment;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 外部化配置 Web 应用引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class ExternalizedConfigurationWebApplicationBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ExternalizedConfigurationWebApplicationBootstrap.class)
                .web(WebApplicationType.SERVLET)
                .properties("management.endpoints.web.exposure.include = env")  // 暴露 Environment Web Endpoint
                .run(args);
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistrationBean(ConfigurableEnvironment environment) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        // 构建 DispatcherServlet 应用上下文
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 复用当前 ApplicationContext 的 ConfigurableEnvironment 对象
        context.setEnvironment(environment);
        // 设置 DispatcherServlet
        servletRegistrationBean.setServlet(new DispatcherServlet(context));
        // 设置 ServletConfig 初始化参数
        servletRegistrationBean.addInitParameter("my-servlet-name", "My DispatcherServlet");
        return servletRegistrationBean;
    }


    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return (servletContext) ->
                servletContext.setInitParameter("web-app-name", "Externalized Configuration Web");
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return factory ->
                factory.addContextCustomizers(
                        context -> {
                            // 设置 JNDI 信息
                            ContextEnvironment environment = new ContextEnvironment();
                            environment.setName("jndi-name");
                            environment.setValue("My JNDI");
                            environment.setType(String.class.getName());
                            // 配置 Environment，等同于 <Environment/> 元素
                            context.getNamingResources().addEnvironment(environment);
                        }
                );
    }

    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                // 激活 JNDI（默认失效）
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }
        };
    }
}
