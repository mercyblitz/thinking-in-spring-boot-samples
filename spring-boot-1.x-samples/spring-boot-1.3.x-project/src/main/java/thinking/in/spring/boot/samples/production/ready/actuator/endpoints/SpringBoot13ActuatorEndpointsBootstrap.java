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
package thinking.in.spring.boot.samples.production.ready.actuator.endpoints;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.util.List;

/**
 * Spring Boot 1.3.8.RELEASE Actuator Endpoints 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class SpringBoot13ActuatorEndpointsBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBoot13ActuatorEndpointsBootstrap.class, EmptyBean.class,
                LiveBeansEndpoint.class) // 导入 "livebeans" Endpoint
//                .parent(parentContext(EmptyBean.class)) // 重新构建 Parent 上下文
                .run(args); // 运行
    }

    private static ConfigurableApplicationContext parentContext(Class... annotatedClasses) {
        // AnnotationConfigApplicationContext 构造器传递配置类，自动 refresh()
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(annotatedClasses);
        context.setId("parent-context"); // 设置 parent 应用上下文 Id 为 parent-context
        return context;
    }

    public static class EmptyBean {
    }

    public static class LiveBeansEndpoint extends AbstractEndpoint<String> implements ApplicationContextAware {

        private final LiveBeansView liveBeansView = new LiveBeansView();

        public LiveBeansEndpoint() {
            // sensitive : false , enabled : true
            super("livebeans", false, true);
        }

        /**
         * @return 直接利用 {@link LiveBeansView#getSnapshotAsJson()} 方法结果输出
         */
        @Override
        public String invoke() {
            return liveBeansView.getSnapshotAsJson();
        }

        @Override
        public void setApplicationContext(ApplicationContext context) throws BeansException {
            // 复制 BeansEndpoint#setApplicationContext(ApplicationContext) 方法实现
            if (context.getEnvironment()
                    .getProperty(LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME) == null) {
                this.liveBeansView.setApplicationContext(context);
            }
        }
    }
}
