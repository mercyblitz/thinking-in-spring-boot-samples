package thinking.in.spring.boot.samples.spring5.context.event;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * {@link EventListener @EventListener 注解驱动事件监听器} 引导类，检验：
 * <ul>
 * <li>{@link EventListener @EventListener} public 方法有返回值是否工作正常</li>
 * <li>在抽象类中声明的{@link EventListener @EventListener} public 方法，是否能在子类 Bean 中正常工作</li>
 * </ul>
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EventListener
 * @since 1.0.0
 */
public class AnnotatedEventListenerBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 @EventListener 类 MyEventListener
        context.register(MyEventListener.class);
        // 初始化上下文
        context.refresh();
        // 关闭上下文
        context.close();
    }

    /**
     * {@link EventListener} 抽象类
     */
    public static abstract class AbstractEventListener {
        @EventListener(ContextRefreshedEvent.class)
        public void onContextRefreshedEvent(ContextRefreshedEvent event) {
            System.out.println("AbstractEventListener : " + event.getClass().getSimpleName());
        }
    }

    /**
     * 具体 {@link EventListener}类，作为 Spring Bean，继承 {@link AbstractEventListener}
     */
    public static class MyEventListener extends AbstractEventListener {

        @EventListener(ContextClosedEvent.class)
        public boolean onContextClosedEvent(ContextClosedEvent event) {
            System.out.println("MyEventListener : " + event.getClass().getSimpleName());
            return true;
        }
    }
}
