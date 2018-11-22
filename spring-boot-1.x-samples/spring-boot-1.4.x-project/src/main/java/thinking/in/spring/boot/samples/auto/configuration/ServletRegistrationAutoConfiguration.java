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
package thinking.in.spring.boot.samples.auto.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServlet;

/**
 * Servlet 注册自动装配
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Configuration
public class ServletRegistrationAutoConfiguration {

    @ConditionalOnClass(name = "org.springframework.boot.context.embedded.ServletRegistrationBean")
    @Bean
    public org.springframework.boot.context.embedded.ServletRegistrationBean servletRegistrationBean() {
        return new org.springframework.boot.context.embedded.ServletRegistrationBean(new NoOpServlet(), "/no-op");
    }

    @ConditionalOnMissingClass("org.springframework.boot.context.embedded.ServletRegistrationBean")
    @Bean
    public ServletRegistrationBean defaultServletRegistrationBean() {
        return new ServletRegistrationBean(new NoOpServlet(), "/no-op");
    }

    private static class NoOpServlet extends HttpServlet {
    }
}
