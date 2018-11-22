package thinking.in.spring.boot.samples.spring5.context.event;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * {@link EventListener @EventListener} 监听多 Spring 事件的引导类，检验：
 * <ul>
 * <li>是否支持单一 {@link ApplicationContextEvent} 参数</li>
 * <li>是否支持</li>
 * </ul>
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see EventListener
 * @since 1.0.0
 */
public class AnnotatedEventListenerOnMultiEventsBootstrap {

    public static void main(String[] args) {
        // 创建 注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 @EventListener 类 MyMultiEventsListener
        context.register(MyMultiEventsListener.class);
        // 初始化上下文
        context.refresh();
        // 关闭上下文
        context.close();
    }

    /**
     * 具体 {@link EventListener}类，提供不同监听多 Spring 事件方法
     */
    public static class MyMultiEventsListener {

        /**
         * 无参数监听 {@link ContextRefreshedEvent} 和 {@link ContextClosedEvent} 事件
         */
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onEvent() {
            System.out.println("onEvent");
        }

        /**
         * 单一 {@link ApplicationContextEvent} 参数监听 {@link ContextRefreshedEvent} 和 {@link ContextClosedEvent} 事件
         *
         * @param event {@link ApplicationContextEvent}
         */
        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
        public void onApplicationContextEvent(ApplicationContextEvent event) {
            System.out.println("onApplicationContextEvent : " + event.getClass().getSimpleName());
        }

//        /**
//         * {@link ContextRefreshedEvent} 和 {@link ContextClosedEvent} 参数监听
//         *
//         * @param refreshedEvent     {@link ContextRefreshedEvent}
//         * @param contextClosedEvent {@link ContextClosedEvent}
//         */
//        @EventListener({ContextRefreshedEvent.class, ContextClosedEvent.class})
//        public void onEvents(ContextRefreshedEvent refreshedEvent, ContextClosedEvent contextClosedEvent) {
//            System.out.println("onEvents : " + refreshedEvent.getClass().getSimpleName()
//                    + " , " + contextClosedEvent.getClass().getSimpleName());
//        }
    }
}
