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

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * {@link PropertySource} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Component
@PropertySource(name = "application.properties", value = "classpath:/application.properties")
@PropertySource(name = "jndi.properties", value = "classpath:/jndi.properties")
public class AnnotationPropertySourceBootstrap {

    public static void main(String[] args) {
        // 创建、扫描并启动注解配置驱动应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationPropertySourceBootstrap.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        // 输出 "user.name" 和 "java.naming.factory.initial" 属性
        System.out.println("user.name = " + environment.getRequiredProperty("user.name"));
        System.out.println("java.naming.factory.initial = " + environment.getRequiredProperty("java.naming.factory.initial"));
        // 关闭上下文
        context.close();
    }

}
