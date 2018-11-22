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
package thinking.in.spring.boot.samples.spring2.bootstrap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 可配置的 Spring {@link ApplicationContext} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ConfigurableApplicationContextBootstrap {

    static {
        // 调整系统属性 "env"，实现 "name" bean 的定义切换
        // envValue 可能来自于 "-D" 命令行启动参数
        // 参数当不存在时，使用 "prod" 作为默认值
        String envValue = System.getProperty("env", "prod");
        System.setProperty("env", envValue);
    }

    public static void main(String[] args) {
        // 定义 XML ApplicationContext
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/configurable-context.xml");
        // "name" bean 对象
        String value = (String) context.getBean("name");
        // "name" bean 内容输出
        System.out.println("Bean 'name' 的内容为：" + value);
        // 关闭上下文
        context.close();
    }
}
