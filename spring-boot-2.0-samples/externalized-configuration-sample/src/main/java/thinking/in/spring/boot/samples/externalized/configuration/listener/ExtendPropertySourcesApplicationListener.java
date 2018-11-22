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

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import thinking.in.spring.boot.samples.externalized.configuration.util.PropertySourceUtils;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * 扩展配置属性源 {@link ApplicationListener} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ExtendPropertySourcesApplicationListener implements SmartApplicationListener {

    // 前实现，处理 ApplicationEnvironmentPreparedEvent
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        // 从事件获取 Environment 对象
        ConfigurableEnvironment environment = event.getEnvironment();
        // 调用工具类 PropertySourceUtils 获取 ResourcePropertySource
        ResourcePropertySource propertySource = PropertySourceUtils.getResourcePropertySource("META-INF/listener.properties");
        // 添加至最高优先级
        environment.getPropertySources().addFirst(propertySource);
    }

    // 处理 ApplicationPreparedEvent
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        // 从事件获取 Environment 对象
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Map<String, Object> source = new HashMap<>();
        // 内部化配置设置 user.name 属性
        source.put("user.name", "马昕曦（小马哥）");
        MapPropertySource propertySource = new MapPropertySource("ApplicationPreparedEvent", source);
        // 添加至最高优先级
        environment.getPropertySources().addFirst(propertySource);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEvent((ApplicationEnvironmentPreparedEvent) event);
        } else if (event instanceof ApplicationPreparedEvent) {
            onApplicationEvent((ApplicationPreparedEvent) event);
        }
    }

    // 参考 ConfigFileApplicationListener 实现
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    // // 参考 ConfigFileApplicationListener 实现
    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}