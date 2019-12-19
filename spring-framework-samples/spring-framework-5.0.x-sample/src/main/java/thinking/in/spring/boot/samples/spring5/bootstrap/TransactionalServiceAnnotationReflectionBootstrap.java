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

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import thinking.in.spring.boot.samples.spring5.annotation.TransactionalService;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link TransactionalService} 注解反射引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@TransactionalService(name = "test") // name 属性内容
public class TransactionalServiceAnnotationReflectionBootstrap {

    public static void main(String[] args) {
        // Class 实现了 AnnotatedElement 接口
        AnnotatedElement annotatedElement = TransactionalServiceAnnotationReflectionBootstrap.class;
        // 从 AnnotatedElement 获取 TransactionalService
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);
        // 显示地调用属性方法 TransactionalService#name() 获取属性
//        String nameAttribute = transactionalService.name();
//        System.out.println("@TransactionalService.name() = " + nameAttribute);

        // 输出 @TransactionalService 属性
//        printAnnotationAttribute(transactionalService);
        // 获取 transactionalService 的所有的元注解
        Set<Annotation> metaAnnotations = getAllMetaAnnotations(transactionalService);
        // 输出结果
        metaAnnotations.forEach(TransactionalServiceAnnotationReflectionBootstrap::printAnnotationAttribute);
    }

    private static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {

        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();

        if (ObjectUtils.isEmpty(metaAnnotations)) { // 没有找到，返回空集合
            return Collections.emptySet();
        }
        // 获取所有非 Java 标准元注解结合
        Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations)
                // 排除 Java 标准注解，如 @Target，@Documented 等，它们因相互依赖，将导致递归不断
                // 通过 java.lang.annotation 包名排除
                .filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());

        // 递归查找元注解的元注解集合
        Set<Annotation> metaMetaAnnotationsSet = metaAnnotationsSet.stream()
                .map(TransactionalServiceAnnotationReflectionBootstrap::getAllMetaAnnotations)
                .collect(HashSet::new, Set::addAll, Set::addAll);

        // 添加递归结果
        metaMetaAnnotationsSet.add(annotation);
        return metaMetaAnnotationsSet;
    }


    private static void printAnnotationAttribute(Annotation annotation) {
        Class<?> annotationType = annotation.annotationType();
        // 完全 Java 反射实现（ReflectionUtils 为 Spring 反射工具类）
        ReflectionUtils.doWithMethods(annotationType,
                method -> System.out.printf("@%s.%s() = %s\n", annotationType.getSimpleName(),
                        method.getName(), ReflectionUtils.invokeMethod(method, annotation)) // 执行 Method 反射调用
//                , method -> method.getParameterCount() == 0); // 选择无参数方法
                , method -> !method.getDeclaringClass().equals(Annotation.class));// 选择非 Annotation 方法
    }
}
