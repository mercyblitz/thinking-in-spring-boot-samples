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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link SpringBootTest} 集成测试
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {"my.name = mercyblitz"},
        classes = AnnotationTestPropertySourceTest.class) // Spring Boot 集成测试注解
public class AnnotationSpringBootTest {

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testGetProperty() {
        System.out.println("my.name = " + environment.getProperty("my.name"));
    }
}

//
//@TestPropertySource(
//        locations = "classpath:/test.properties", // 加载外部属性资源文件，包含 "my.name = 小马哥"，"my.age = 32"
//        properties = "my.name = mercyblitz") // 覆盖 test.properties "my.age = 32"
