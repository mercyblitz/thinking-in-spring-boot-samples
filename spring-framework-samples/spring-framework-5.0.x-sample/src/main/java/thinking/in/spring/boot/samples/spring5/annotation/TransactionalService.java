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
package thinking.in.spring.boot.samples.spring5.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link Transactional @Transactional} 和 {@link Service @Service} 组合注解
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Transactional
 * @see Service
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Transactional
@Service(value = "transactionalService")
public @interface TransactionalService {

    /**
     * @return 服务 Bean 名称
     */
    @AliasFor(attribute = "value")
    String name() default "";

    /**
     * 覆盖 {@link Transactional#value()} 默认值
     *
     * @return {@link PlatformTransactionManager} Bean 名称
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 建立 {@link Transactional#transactionManager()} 别名
     *
     * @return {@link PlatformTransactionManager} Bean 名称，默认关联 "txManager" Bean
     */
    @AliasFor(attribute = "transactionManager", annotation = Transactional.class)
    String manager() default "txManager";

}





