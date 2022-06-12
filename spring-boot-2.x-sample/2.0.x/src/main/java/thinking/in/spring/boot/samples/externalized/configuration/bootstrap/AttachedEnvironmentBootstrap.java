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
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link ConfigurationPropertySources#attach(Environment)} 后的{@link Environment} 示例引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class AttachedEnvironmentBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(AttachedEnvironmentBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .properties("relaxed.NAME=小马哥")
                        .run(args);
        // 获取 ConfigurableEnvironment 对象
        ConfigurableEnvironment environment = context.getEnvironment();

        System.out.printf("Environment 获取属性 - 松散方式 relaxed.name ：%s , 原始方式 relaxed.NAME ：%s\n",
                environment.getProperty("relaxed.name"), environment.getProperty("relaxed.NAME"));

        // 从 Environment 关联的 MutablePropertySources 移除 ConfigurationPropertySourcesPropertySource
        System.out.printf("PropertySource[ %s ] 已从 Environment 中删除！\n",
                environment.getPropertySources().remove("configurationProperties"));

        System.out.printf("Environment 获取属性 - 松散方式 relaxed.name ：%s , 原始方式 relaxed.NAME ：%s\n",
                environment.getProperty("relaxed.name"), environment.getProperty("relaxed.NAME"));

        // 关闭上下文
        context.close();
    }
}
