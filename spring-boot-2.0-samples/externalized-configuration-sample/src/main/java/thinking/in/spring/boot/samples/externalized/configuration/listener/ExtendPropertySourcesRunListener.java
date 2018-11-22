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
package thinking.in.spring.boot.samples.externalized.configuration.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePropertySource;
import thinking.in.spring.boot.samples.externalized.configuration.util.PropertySourceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展配置属性源 {@link SpringApplicationRunListener} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE) // 最高优先级
public class ExtendPropertySourcesRunListener implements SpringApplicationRunListener {

    /**
     * 约定的构造器签名
     *
     * @param application {@link SpringApplication}
     * @param args        启动参数
     */
    public ExtendPropertySourcesRunListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        // 调用工具类 PropertySourceUtils 获取 ResourcePropertySource
        ResourcePropertySource propertySource = PropertySourceUtils.getResourcePropertySource("/META-INF/run-listener.properties");
        // 添加至最高优先级
        environment.getPropertySources().addFirst(propertySource);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        // 从 ConfigurableApplicationContext 获取 ConfigurableEnvironment
        ConfigurableEnvironment environment = context.getEnvironment();
        Map<String, Object> source = new HashMap<>();
        // 内部化配置设置 user.name 属性
        source.put("user.name", "mercyblitz 2018");
        MapPropertySource mapPropertySource = new MapPropertySource("contextPrepared", source);
        environment.getPropertySources().addFirst(mapPropertySource);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        // 从 ConfigurableApplicationContext 获取 ConfigurableEnvironment
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        // 通过名称获取名为 "systemProperties" 的 PropertySource（实现使用常量）
        PropertySource propertySource = propertySources.get(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME);
        // 将 "systemProperties" 的 PropertySource 添加在 "contextPrepared" 之前（由 contextPrepared 方法添加），提高优先级
        propertySources.addBefore("contextPrepared", propertySource);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
    }

}
