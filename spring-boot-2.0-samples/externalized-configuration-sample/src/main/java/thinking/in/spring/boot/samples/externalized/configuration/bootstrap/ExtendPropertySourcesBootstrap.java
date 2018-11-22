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
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import thinking.in.spring.boot.samples.externalized.configuration.util.PropertySourceUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 扩展 {@link PropertySources} 引导启动类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class ExtendPropertySourcesBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        // 获取 Environment
        ConfigurableEnvironment environment = context.getEnvironment();
        // 加载 META-INF/runtime.properties 配置文件
        ResourcePropertySource propertySource = PropertySourceUtils.getResourcePropertySource("META-INF/runtime.properties");
        // 追加至 PropertySources 顶端
        environment.getPropertySources().addFirst(propertySource);
        // 读取 user.name 属性内容
        System.out.println("从 Environment 读取属性 user.name = " + environment.getProperty("user.name"));
        System.out.println("Environment 所有 PropertySource : ");

        environment.getPropertySources().forEach(source -> {
            System.out.printf("\t %s\n", source);
        });
        // 关闭上下文
        context.close();
    }
}
