package thinking.in.spring.boot.samples.spring5.context.event;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link EventListener @EventListener} {@link Async 异步}监听方法引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EventListener
 * @see Async
 * @since 1.0.0
 */
public class AnnotatedAsyncEventListenerBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 异步 @EventListener 类 MyAsyncEventListener
        context.register(MyAsyncEventListener.class);
        println(" Spring 应用上下文正在初始化...");
        // 初始化上下文
        context.refresh();
        // 关闭上下文
        context.close();
    }

    @EnableAsync // 需要激活异步，否则 @Async 无效
    public static class MyAsyncEventListener {

        @EventListener(ContextRefreshedEvent.class)
        @Async
        public Boolean ontextRefreshedEvent(ContextRefreshedEvent event) {
            println(" MyAsyncEventListener : " + event.getClass().getSimpleName());
            return true;
        }
    }

    /**
     * 输出内容并附加当前线程信息
     *
     * @param content 输出内容
     */
    private static void println(String content) {
        // 当前线程名称
        String threadName = Thread.currentThread().getName();
        System.out.println("[ 线程 " + threadName + " ] : " + content);
    }
}
