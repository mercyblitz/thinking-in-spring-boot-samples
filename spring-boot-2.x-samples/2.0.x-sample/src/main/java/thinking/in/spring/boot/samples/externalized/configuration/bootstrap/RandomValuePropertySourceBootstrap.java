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
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link RandomValuePropertySource} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class RandomValuePropertySourceBootstrap {

    static {
        // 使用更高优先级的 Java 系统属性覆盖 "random.int(10)" 属性
        System.setProperty("random.int(10)", "95");
    }

    public static void main(String[] args) {
        // Java 系统属性值注入随机数（优先级高于 RandomValuePropertySource）
        System.setProperty("sys.value", "${random.int(10)}");
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(
                        RandomValuePropertySourceBootstrap.class)
                        // SpringApplication Properties 值注入随机数（优先级最低）
                        .properties("props.value = ${random.int(10)}")
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);

        ConfigurableEnvironment environment = context.getEnvironment();
        // 读取 Java 系统属性
        System.out.println("sys.value = " + environment.getProperty("sys.value"));
        // 读取 SpringApplication Properties  配置
        System.out.println("props.value = " + environment.getProperty("props.value"));
        // 关闭上下文
        context.close();
    }
}
