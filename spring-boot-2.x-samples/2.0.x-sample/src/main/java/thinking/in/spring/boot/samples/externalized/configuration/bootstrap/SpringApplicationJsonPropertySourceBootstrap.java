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
package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

/**
 * SPRING_APPLICATION_JSON {@link PropertySource} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class SpringApplicationJsonPropertySourceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        SpringApplicationJsonPropertySourceBootstrap.class)
                        // SpringApplication Properties 设置 JSON内容（优先级最低）
                        .properties("spring.application.json={\"my.name\":\"小马哥\"}")
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        ConfigurableEnvironment environment = context.getEnvironment();
        // 读取 spring.application.json 属性
        System.out.println("my.name = " + environment.getProperty("my.name"));
        // 关闭上下文
        context.close();
    }
}
