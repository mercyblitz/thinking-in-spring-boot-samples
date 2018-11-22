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
import org.springframework.core.env.CommandLinePropertySource;

/**
 * {@link CommandLinePropertySource} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class CommandLinePropertySourceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(CommandLinePropertySourceBootstrap.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run("--user.name=xiaomage"); // 显示地传递命令行参数选项，替换由 main 方法透传的进程启动参数 args

        // user.name 存在三处属性配置（按照优先级高到低排序）：
        // 1. 来自于命令行参数 user.name=xiaomage
        // 2. 来自于Java 系统属性 user.name = mercyblitz
        // 3. 来自于application.properties user.name = 小马哥
        System.out.println("user.name  = " + context.getEnvironment().getProperty("user.name"));
        // 关闭上下文
        context.close();

    }
}
