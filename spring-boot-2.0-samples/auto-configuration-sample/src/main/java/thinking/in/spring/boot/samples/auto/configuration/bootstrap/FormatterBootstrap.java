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

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import thinking.in.spring.boot.samples.autoconfigure.formatter.Formatter;
import thinking.in.spring.boot.samples.autoconfigure.formatter.FormatterAutoConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Formatter} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see FormatterAutoConfiguration
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class FormatterBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FormatterBootstrap.class)
                .web(WebApplicationType.NONE)  // 非 Web 应用
//                 .properties("formatter.enabled=true") // 配置默认属性, "=" 前后不能有空格
                .run(args);                    // 运行
        // 待格式化对象
        Map<String, Object> data = new HashMap<>();
        data.put("name", "小马哥");
        // 获取 Formatter 来自于 FormatterAutoConfiguration
        Map<String, Formatter> beans = context.getBeansOfType(Formatter.class);
        if (beans.isEmpty()) { // 如果 Bean 不存在，抛出异常
            throw new NoSuchBeanDefinitionException(Formatter.class);
        }
        beans.forEach((beanName, formatter) -> {
            System.out.printf("[Bean name : %s] %s.format(data) : %s\n", beanName, formatter.getClass().getSimpleName(),
                    formatter.format(data));
        });

        //  关闭当前上下文
        context.close();
    }
}
