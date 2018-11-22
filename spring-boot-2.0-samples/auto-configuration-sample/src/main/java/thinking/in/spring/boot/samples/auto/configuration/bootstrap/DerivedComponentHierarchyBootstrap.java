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
package thinking.in.spring.boot.samples.auto.configuration.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * {@link Component} 派生层次性引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Component
 * @since 1.0.0
 */
@SpringBootApplication
public class DerivedComponentHierarchyBootstrap {

    public static void main(String[] args) {
        // 当前引导类
        Class<?> bootstrapClass = DerivedComponentHierarchyBootstrap.class;
        // 运行 Spring Boot，并返回 Spring 应用上下文
        ConfigurableApplicationContext context = new SpringApplicationBuilder(bootstrapClass)
                .web(WebApplicationType.NONE) // 非 Web 类型
                .run();
        System.out.println("当前引导类 Bean ：" + context.getBean(bootstrapClass));
        // 关闭 Spring 应用上下文
        context.close();
    }
}
