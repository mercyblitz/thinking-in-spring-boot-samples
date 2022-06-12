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

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link TestPropertySource} 集成测试
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
        locations = "classpath:/test.properties", // 加载外部属性资源文件
        properties = {
                "my.age = 18", // 覆盖 test.properties 中配置
                "my.book-name = Spring Boot 编程思想",
//                "PATH = /home/mercyblitz" // 覆盖 Java 系统属性、OS 环境变量、@PropertySource
        }
)
@Configuration // 配合 @PropertySource 使用。缺少时，@PropertySource 无法被解析
@PropertySource(name = "app", value = "classpath:/app.properties") // 属性 PATH = /home/
//@ContextConfiguration(classes = AnnotationTestPropertySourceTest.class) // Spring 集成测试注解
@SpringBootTest(
        properties = "PATH = /home/abc",
        classes = AnnotationTestPropertySourceTest.class) // Spring Boot 集成测试注解
public class AnnotationTestPropertySourceTest {

    @Autowired
    private ConfigurableEnvironment environment;

    @BeforeClass
    public static void init() { // 准备方法
        System.setProperty("PATH", "/home/mercy"); // 设置 Java 系统属性
    }

    @Test
    public void testPrecedence() {
        // 获取 @PropertySource(name = "app", value = "classpath:/app.properties")  的 PropertySource
        org.springframework.core.env.PropertySource propertySource = environment.getPropertySources().get("app");
        System.out.println("[OS 环境变量     ] PATH  = " + System.getenv().get("PATH"));
        System.out.println("[@PropertySource] PATH  = " + propertySource.getProperty("PATH"));
        System.out.println("[Java 系统属性   ] PATH = " + System.getProperty("PATH"));
        System.out.println("[Environment    ] PATH = " + environment.getProperty("PATH"));
    }

    @Test
    public void testFindPropertyLocation() {
        System.out.println("[Environment    ] PATH = " + environment.getProperty("PATH"));
        for (org.springframework.core.env.PropertySource propertySource : environment.getPropertySources()) {
            if (propertySource.containsProperty("PATH")) {
                System.out.println("PATH = " + propertySource.getProperty("PATH") + " 位于 " + propertySource);
            }
        }
    }

    @Test
    public void testGetPropertySources() {
        // 输出 来自于 @SpringBootTest PATH 属性
        System.out.println("[Environment    ] PATH = " + environment.getProperty("PATH"));
        // 输出 PropertySource 数量
        System.out.printf("Environment 关联 PropertySource 总数：%d，分别为：\n", environment.getPropertySources().size());
        environment.getPropertySources().forEach(propertySource -> System.out.println(propertySource));
    }

    @Test
    public void test() {
        // 从 classpath:/test.properties 文件中 读取 my.name 属性
        String myName = environment.getProperty("my.name");
        // 从 properties() 读取 my.book-name 检验中文字符
        String myBookName = environment.getProperty("my.book-name");
        // 从 properties() 读取覆盖 my.age
        int age = environment.getProperty("my.age", int.class);
        System.out.printf("my.name = %s , myBookName = %s, my.age = %d \n", myName, myBookName, age);
    }
}

