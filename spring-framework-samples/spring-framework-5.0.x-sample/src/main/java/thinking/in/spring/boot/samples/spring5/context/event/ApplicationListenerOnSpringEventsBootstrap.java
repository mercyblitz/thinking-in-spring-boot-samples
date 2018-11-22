package thinking.in.spring.boot.samples.spring5.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 监听 Spring 内置事件引导类，通过 {@link ConfigurableApplicationContext#addApplicationListener(ApplicationListener)}}
 * 方法注册 {@link ApplicationListener} 实例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ConfigurableApplicationContext
 * @see ApplicationEvent
 * @see ApplicationListener
 * @since 1.0.0
 */
public class ApplicationListenerOnSpringEventsBootstrap {

    public static void main(String[] args) {
        // 创建 ConfigurableApplicationContext 实例 GenericApplicationContext
        ConfigurableApplicationContext context = new GenericApplicationContext();
        System.out.println("创建 Spring 应用上下文 : " + context.getDisplayName());
        // 添加 ApplicationListener 非泛型实现
        context.addApplicationListener(event ->
                System.out.println(event.getClass().getSimpleName())
        );

        // refresh() : 初始化应用上下文
        System.out.println("应用上下文准备初始化...");
        context.refresh(); // 发布 ContextRefreshedEvent
        System.out.println("应用上下文已初始化...");

        // stop() : 停止应用上下文
        System.out.println("应用上下文准备停止启动...");
        context.stop();    // 发布 ContextStoppedEvent
        System.out.println("应用上下文已停止启动...");

        // start(): 启动应用上下文
        System.out.println("应用上下文准备启动启动...");
        context.start();  // 发布 ContextStartedEvent
        System.out.println("应用上下文已启动启动...");

        // close() : 关闭应用上下文
        System.out.println("应用上下文准备关闭...");
        context.close();  // 发布 ContextClosedEvent
        System.out.println("应用上下文已关闭...");

    }
}
