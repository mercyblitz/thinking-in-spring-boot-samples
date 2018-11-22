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
package thinking.in.spring.boot.samples.spring3.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 多层次{@link Component @Component}派生注解 引导类
 * <p>
 * 读者调整本工程 pom.xml 文件中 spring.version 属性切换不同的 Spring Framework 版本，
 * 体验不同的多层次 {@link Component @Component} "派生性"的运行结果
 * <ul>
 * <li>当 Spring Framework 版本小于 4.0 时，运行结果分别为：<code>true</code> 和 <code>false</code></li>
 * <li>当 Spring Framework 版本等于或大于 4.0 时，运行结果分别为：<code>true</code> 和 <code>true</code></li>
 * </ul>
 * <p>
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class HierarchicalDerivedComponentAnnotationBootstrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/context.xml");
        // 检验 myFirstLevelRepository 以及 mySecondLevelRepository 是否存在
        System.out.println("myFirstLevelRepository Bean 是否存在：" + context.containsBean("myFirstLevelRepository"));
        System.out.println("mySecondLevelRepository Bean 是否存在：" + context.containsBean("mySecondLevelRepository"));
        // 关闭上下文
        context.close();
    }
}
