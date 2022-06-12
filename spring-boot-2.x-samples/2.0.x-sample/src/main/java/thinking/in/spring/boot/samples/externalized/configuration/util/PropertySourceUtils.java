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
package thinking.in.spring.boot.samples.externalized.configuration.util;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

/**
 * {@link PropertySource} 工具类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public abstract class PropertySourceUtils {

    /**
     * 获取指定资源位置的 {@link ResourcePropertySource}
     *
     * @param location 资源位置
     * @return {@link ResourcePropertySource}
     * @throws IllegalStateException
     */
    public static ResourcePropertySource getResourcePropertySource(String location) {
        // 创建 ResourceLoader 实例
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // 通过 location 获取 Resource
        Resource resource = resourceLoader.getResource(location);
        // EncodedResource 需指定字符编码，避免中文乱码
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 将 EncodedResource 转化为 ResourcePropertySource 对象
        ResourcePropertySource propertySource = null;
        try {
            propertySource = new ResourcePropertySource(encodedResource);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return propertySource;
    }

}
