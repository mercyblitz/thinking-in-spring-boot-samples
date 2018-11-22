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

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;
import thinking.in.spring.boot.samples.spring5.bean.TransactionalServiceBean;

import java.util.Map;

/**
 * {@link TransactionalServiceBean} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackageClasses = TransactionalServiceBean.class) // 扫描 TransactionalServiceBean 所在 package
@EnableTransactionManagement // 激活事务管理
public class TransactionalServiceBeanBootstrap {

    public static void main(String[] args) {
        // 注册当前引导类作为 Configuration Class
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(TransactionalServiceBeanBootstrap.class);
        // 获取所有 TransactionalServiceBean 类型 Bean，其中 Key 为 Bean 名称
        Map<String, TransactionalServiceBean> beansMap = context.getBeansOfType(TransactionalServiceBean.class);
        beansMap.forEach((beanName, bean) -> {
            System.out.printf("Bean 名称 : %s , 对象 : %s\n", beanName, bean);
            bean.save();
        });
        context.close();
    }

    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager : 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
            }
        };
    }

    @Bean("txManager2")
    public PlatformTransactionManager txManger2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManger2 : 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
            }
        };
    }
}



