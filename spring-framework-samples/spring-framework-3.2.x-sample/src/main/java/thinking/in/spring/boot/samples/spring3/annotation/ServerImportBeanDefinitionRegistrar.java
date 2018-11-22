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
package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import thinking.in.spring.boot.samples.spring3.server.Server;

import java.util.stream.Stream;

/**
 * {@link Server} {@link ImportBeanDefinitionRegistrar} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 复用 {@link ServerImportSelector} 实现，避免重复劳动
        ImportSelector importSelector = new ServerImportSelector();
        // 筛选 Class 名称集合
        String[] selectedClassNames = importSelector.selectImports(importingClassMetadata);
        // 创建 Bean 定义
        Stream.of(selectedClassNames)
                .map(BeanDefinitionBuilder::genericBeanDefinition) // 转化为 BeanDefinitionBuilder 对象
                .map(BeanDefinitionBuilder::getBeanDefinition)     // 转化为 BeanDefinition
                .forEach(beanDefinition ->
                        // 注册 BeanDefinition 到 BeanDefinitionRegistry
                        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry)
                );
    }
}
