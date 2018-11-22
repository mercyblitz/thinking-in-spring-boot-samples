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

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import thinking.in.spring.boot.samples.spring5.annotation.TransactionalService;

import java.io.IOException;
import java.util.Set;

/**
 * {@link TransactionalService @TransactionalService} 注解元信息引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@TransactionalService
public class TransactionalServiceAnnotationMetadataBootstrap {

    public static void main(String[] args) throws IOException {
        // @TransactionalService 标注在当前类 TransactionalServiceAnnotationMetadataBootstrap
        String className = TransactionalServiceAnnotationMetadataBootstrap.class.getName();
        // 构建 MetadataReaderFactory 实例
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        // 读取 @TransactionService MetadataReader 信息
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);
        // 读取 @TransactionService AnnotationMetadata 信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        annotationMetadata.getAnnotationTypes().forEach(annotationType -> {

            Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);

            metaAnnotationTypes.forEach(metaAnnotationType -> {
                System.out.printf("注解 @%s 元标注 @%s\n", annotationType, metaAnnotationType);
            });

        });
    }
}
