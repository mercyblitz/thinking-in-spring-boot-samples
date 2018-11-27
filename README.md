# 《Spring Boot 编程思想》示例工程

《Spring Boot 编程思想》所有的示例代码均存放在 GitHub 工程 [https://github.com/mercyblitz/thinking-in-spring-boot-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples)，该工程为标准的 Maven 多模块工程，运行时要求为 [Java](https://www.oracle.com/technetwork/java/index.html) `1.8+` 以及 [Maven](https://maven.apache.org/) `3.2.5+`。其协议为 "[Apache License Version 2.0](https://github.com/mercyblitz/thinking-in-spring-boot-samples/blob/master/LICENSE)"，不必担心商业用途所带来的风险。由于本书尚未完全截稿，工程结构未来可能存在微调。因此，当前内容无法确保百分之百匹配，请读者定期关注 [`README.md`](https://github.com/mercyblitz/thinking-in-spring-boot-samples) 文件，确保咨询的更新。




### 工程结构

示例工程 `thinking-in-spring-boot-samples` 包含五个子模块以及四个文件，分别是：

- 子模块
  
| 子模块                                                       | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [shared-libraries](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/shared-libraries) | 共享类库，为其他工程提供基础 API 或依赖                      |
| [spring-boot-1.x-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples) | Spring Boot 1.x 示例工程，包含六个子模块，主要用于参考和对比 Spring Boot 1.x 各版本中的实现差异，并且提供章节示例代码实现 |
| [spring-boot-2.0-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples) | Spring Boot 2.0 示例工程，也是主示例工程，以 `2.0.2.RELEASE` 作为基础版本 |
| [spring-framework-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples) | Spring Framework 示例工程，作为 Spring Boot 底层实现框架，版本范围从 2.0 到 5.0 |
| [traditional-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/traditional-samples) | 传统 Java EE 示例工程，用于理解 Java EE 与 Spring Boot 关联和差异 |

- 文件

| 文件                                                         | 说明                          |
| ------------------------------------------------------------ | ----------------------------- |
| [.gitignore](https://github.com/mercyblitz/thinking-in-spring-boot-samples/blob/master/.gitignore) | Git 版本控制忽略文件          |
| [LICENSE](https://github.com/mercyblitz/thinking-in-spring-boot-samples/blob/master/LICENSE) | 工程许可文件                  |
| [README.md](https://github.com/mercyblitz/thinking-in-spring-boot-samples/blob/master/README.md) | 工程说明文件                  |
| [pom.xml](https://github.com/mercyblitz/thinking-in-spring-boot-samples/blob/master/pom.xml) | 示例工程 Maven `pom.xml` 文件 |

其中，又以 [spring-boot-2.0-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples) 、[spring-boot-1.x-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples) 以及 [spring-framework-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples) 为本示例工程最核心的子模块，对此将详细说明。




#### 子模块 [spring-boot-2.0-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples)

[spring-boot-2.0-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples) 作为《Spring Boot 编程思想》的主示例工程，基于 Spring Boot `2.0.2.RELEASE` 实现，由若干个子模块组成，这些模块与章节所讨论的议题紧密关联：

```
├── spring-boot-2.0-samples
│   ├── auto-configuration-sample
│   ├── externalized-configuration-sample
│   ├── first-app-by-gui
│   ├── first-spring-boot-application
│   ├── formatter-spring-boot-starter
│   ├── pom.xml
│   ├── production-ready-sample
│   ├── spring-application-sample
│   ├── spring-boot-2.0-samples.iml
│   └── traditional-web-sample
```

按照本书章节的安排，模块与章节所对应的关系依次为：

| 子模块                                                       | 说明                                                         | 篇章                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------- |
| [first-spring-boot-application](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/first-spring-boot-application) | 基于 Maven 插件构建的第一个 Spring Boot 应用                 | 《核心篇 - 总览Spring Boot》          |
| [first-app-by-gui](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/first-app-by-gui) | 基于图形化界面 https://start.spring.io/ 构建的第一个 Spring Boot 应用 | 《核心篇 - 总览Spring Boot》          |
| [auto-configuration-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/auto-configuration-sample) | Spring Boot 自动装配示例                                     | 《核心篇 - 走向自动装配》             |
| [formatter-spring-boot-starter](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/formatter-spring-boot-starter) | Spring Boot 自动装配 Starter 示例                            | 《核心篇 - 走向自动装配》             |
| [spring-application-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/spring-application-sample) | Spring Boot `SpringApplication` 示例                          | 《核心篇 - 理解 `SpringApplication`》 |
| [externalized-configuration-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/externalized-configuration-sample) | Spring Boot 外部化配置示例                                   | 《运维篇 - 超越外部化配置》           |
| [production-ready-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/production-ready-sample) | Spring Boot Production-Ready                                 | 《运维篇 - 简化 Spring 应用运维体系》 |
| [traditional-web-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples/traditional-web-sample) | Spring Boot 应用部署到传统 Servlet 容器示例                  | 《Web篇 - “渐行渐远“的 Servlet》      |

除此之外，相关示例代码部分也可能放置在其他子模块，如 “走向自动装配” 章节中，大量的实例代码在子模块 [spring-framework-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples) 之中。




#### 子模块 [spring-boot-1.x-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples)

前文提到，该[子模块](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples) 包含六个子模块，它们对应了所有的 Spring Boot 1.x 实现版本：

```
├── spring-boot-1.x-samples
│   ├── pom.xml
│   ├── spring-boot-1.0.x-project
│   ├── spring-boot-1.1.x-project
│   ├── spring-boot-1.2.x-project
│   ├── spring-boot-1.3.x-project
│   ├── spring-boot-1.4.x-project
│   ├── spring-boot-1.5.x-project
```

由于截止到当前编写时间，恰逢 Spring Boot 1.5 的发行版本为 `1.5.10.RELEASE`，而1.5 之前的版本则已停止维护，可选择其最后发行的版本作为参考，故子模块、Spring Boot 1.x 版本以及对应 Spring Framework 的关系，如下表格所示：

| 子模块                                                       | Spring Boot 1.x 版本 | Spring Framework 版本 |
| ------------------------------------------------------------ | -------------------- | --------------------- |
| [spring-boot-1.0.x-project](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples/spring-boot-1.0.x-project) | `1.0.2.RELEASE`      | `4.0.3.RELEASE`       |
| [spring-boot-1.1.x-project](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples/spring-boot-1.1.x-project) | `1.1.9.RELEASE`      | `4.0.8.RELEASE`       |
| [spring-boot-1.2.x-project](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples/spring-boot-1.2.x-project) | `1.2.8.RELEASE`      | `4.1.9.RELEASE`       |
| [spring-boot-1.3.x-project](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples/spring-boot-1.3.x-project) | `1.3.8.RELEASE`      | `4.2.8.RELEASE`       |
| [spring-boot-1.4.x-project](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples/spring-boot-1.4.x-project) | `1.4.7.RELEASE`      | `4.3.9.RELEASE`       |
| [spring-boot-1.5.x-project](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples/spring-boot-1.5.x-project) | `1.5.10.RELEASE`     | `4.3.14.RELEASE`      |

值得注意的是，子模块 [spring-boot-1.x-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples) 并非主示例工程，各章节讨论的特性示例并非面面俱到。相反，读者应重点关注子模块 [spring-boot-2.0-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-2.0-samples)。




#### 子模块 [spring-framework-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples)

[spring-framework-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples) 作为例实现的辅助分析子模块，其版本涵盖 2.0 到 5.0，由于 [spring-boot-1.x-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples) 间接引入了 Spring Framework 4.0 到 4.3 的依赖，因此当前模块并未将 4.x 版本细分：

```
├── spring-framework-samples
│   ├── pom.xml
│   ├── spring-framework-2.0.x-sample
│   ├── spring-framework-2.5.6-sample
│   ├── spring-framework-3.0.x-sample
│   ├── spring-framework-3.1.x-sample
│   ├── spring-framework-3.2.x-sample
│   ├── spring-framework-4.3.x-sample
│   ├── spring-framework-5.0.x-sample
│   └── spring-webmvc-3.2.x-sample
```

原则上，以上模块所选择 Spring Framework 版本与 [spring-boot-1.x-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-boot-1.x-samples) 类似，故子模块与 Spring Framework 依赖关系如下所示：

| 子模块                                                       | Spring Framework 版本 |
| ------------------------------------------------------------ | --------------------- |
| [spring-framework-2.0.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-2.0.x-sample) | `2.0.8`               |
| [spring-framework-2.5.6-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-2.5.6-sample) | `2.5.6.SEC03`         |
| [spring-framework-3.0.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-3.0.x-sample) | `3.0.0.RELEASE`       |
| [spring-framework-3.1.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-3.1.x-sample) | `3.1.4.RELEASE`       |
| [spring-framework-3.2.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-3.2.x-sample) | `3.2.18.RELEASE`      |
| [spring-framework-4.3.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-4.3.x-sample) | `4.3.17.RELEASE`      |
| [spring-framework-5.0.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-framework-5.0.x-sample) | `5.0.6.RELEASE`       |
| [spring-webmvc-3.2.x-sample](https://github.com/mercyblitz/thinking-in-spring-boot-samples/tree/master/spring-framework-samples/spring-webmvc-3.2.x-sample) | `3.2.18.RELEASE`      |

当读者发现子模块工程为仅包含 `pom.xml` 文件时，说明它引入的目地在于 Spring Framework 源码分析，用于比对版本间 Spring 特性的变迁和实现的差异。了解了示例工程的结构后，下一节的内容将接入示例代码的说明。




### 示例代码说明

由于本书几乎覆盖所有的 Spring Framework 以及 Spring Boot 版本，通常在配合章节说明时，绝大多数情况，示例代码的结尾部分带有 ”源码位置“的信息，且相对于 [https://github.com/mercyblitz/thinking-in-spring-boot-samples](https://github.com/mercyblitz/thinking-in-spring-boot-samples) 工程路径，如：

```java
public class GenericEventListenerBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 UserEventListener，即实现 ApplicationListener ，也包含 @EventListener 方法
        context.register(UserEventListener.class);
        // 初始化上下文
        context.refresh();
        // 构造泛型事件
        GenericEvent<User> event = new GenericEvent(new User("小马哥"));
        // 发送泛型事件
        context.publishEvent(event);
        // 发送 User 对象作为事件源
        context.publishEvent(new User("mercyblitz"));
        // 关闭上下文
        context.close();
    }
	...
}
```

> 源码位置：以上示例代码，读者可查找 **spring-framework-samples/spring-framework-5.0.x-sample** 工程

如果以上信息尚未提供，默认情况，如书中的 Spring Framework 示例存放在  **spring-framework-samples/spring-framework-5.0.x-sample** 工程，而 Spring Boot 示例存放在 **spring-boot-2.0-samples/** 所对应的章节工程。
