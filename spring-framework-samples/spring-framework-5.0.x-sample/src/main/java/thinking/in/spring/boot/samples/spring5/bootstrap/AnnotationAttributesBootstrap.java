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
package thinking.in.spring.boot.samples.spring5.bootstrap;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thinking.in.spring.boot.samples.spring5.annotation.TransactionalService;
import thinking.in.spring.boot.samples.spring5.bean.TransactionalServiceBean;

import java.lang.reflect.AnnotatedElement;

/**
 * {@link AnnotationAttributes} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class AnnotationAttributesBootstrap {

    public static void main(String[] args) {

//        AnnotatedElement annotatedElement = TransactionalService.class;

        AnnotatedElement annotatedElement = TransactionalServiceBean.class;

        // 获取 @Service 注解属性独享
        AnnotationAttributes serviceAttributes =
                AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);

        // 获取 @Transactional 注解属性独享
        AnnotationAttributes transactionalAttributes =
                AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);

        // 输出
        print(serviceAttributes);

        print(transactionalAttributes);
    }

    private static void print(AnnotationAttributes annotationAttributes) {

        System.out.printf("注解 @%s 属性集合 : \n", annotationAttributes.annotationType().getName());

        annotationAttributes.forEach((name, value) ->
                System.out.printf("\t属性 %s : %s \n", name, value)
        );
    }
}
