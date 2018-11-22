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
package thinking.in.spring.boot.samples.spring4.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解 {@link Lookup} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Configuration
public class AnnotationLookupBootstrap {

    @Lookup("helloWorld2")
    @Bean("helloWorld")
    public String helloWorld() {
        return "Hello,World";
    }

    @Autowired
    @Qualifier("helloWorld")
    private String helloWorld;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 AnnotationLookupBootstrap Configuration Class
        context.register(AnnotationLookupBootstrap.class);
        // 启动上下文
        context.refresh();
        System.out.printf("helloWorld Bean : %s\n", context.getBean("helloWorld"));

        AnnotationLookupBootstrap bootstrap = context.getBean(AnnotationLookupBootstrap.class);

        System.out.println(bootstrap.helloWorld);

        // 关闭
        context.close();
    }
}
