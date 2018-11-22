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
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import thinking.in.spring.boot.samples.spring5.annotation.TransactionalService;

import java.io.IOException;

/**
 * {@link AnnotationMetadata} 实现性能比较引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class AnnotationMetadataPerformanceBootstrap {

    public static void main(String[] args) throws IOException {

        // 反射实现
        AnnotationMetadata standardAnnotationMetadata = new StandardAnnotationMetadata(TransactionalService.class);

        SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();

        MetadataReader metadataReader = factory.getMetadataReader(TransactionalService.class.getName());
        // ASM 实现
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        int times = 10 * 10000; // 10 万次

        testAnnotationMetadataPerformance(standardAnnotationMetadata, times);
        testAnnotationMetadataPerformance(annotationMetadata, times);

        times = 100 * 10000;    // 100 万次

        testAnnotationMetadataPerformance(standardAnnotationMetadata, times);
        testAnnotationMetadataPerformance(annotationMetadata, times);

        times = 1000 * 10000;   // 1000 万次

        testAnnotationMetadataPerformance(standardAnnotationMetadata, times);
        testAnnotationMetadataPerformance(annotationMetadata, times);

        times = 10000 * 10000; // 1 亿次

        testAnnotationMetadataPerformance(standardAnnotationMetadata, times);
        testAnnotationMetadataPerformance(annotationMetadata, times);
    }

    private static void testAnnotationMetadataPerformance(AnnotationMetadata annotationMetadata, int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            annotationMetadata.getAnnotationTypes();
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.printf("%d 次 %s.getAnnotationTypes() 方法执行消耗 %s ms\n",
                times, annotationMetadata.getClass().getSimpleName(), costTime);
    }
}
