package thinking.in.spring.boot.samples.spring.application.event.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import thinking.in.spring.boot.samples.spring.application.event.MultipleSpringBootEventsListenerBootstrap;

import java.util.Random;

/**
 * 多 Spring Boot 事件 {@link SmartApplicationListener 监听器}实现，监听事件如下：
 * <ul>
 * <li>{@link ApplicationReadyEvent}</li>
 * <li>{@link ApplicationFailedEvent}</li>
 * </ul>
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see SmartApplicationListener
 * @see ApplicationReadyEvent
 * @see ApplicationFailedEvent
 * @see Ordered
 * @see MultipleSpringBootEventsListenerBootstrap
 * @since 1.0.0
 */
public class MultipleSpringBootEventsListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        // 支持事件的类型
        return ApplicationReadyEvent.class.equals(eventType) ||
                ApplicationFailedEvent.class.equals(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        // SpringApplicationEvent 均已 SpringApplication 作为配置源
        return SpringApplication.class.equals(sourceType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            // 当事件为 ApplicationReadyEvent 时，随机的抛出异常
            if (new Random().nextBoolean()) {
                throw new RuntimeException("ApplicationReadyEvent 事件监听异常!");
            }
        }
        System.out.println("MultipleSpringBootEventsListener 监听到事件 : " + event.getClass().getSimpleName());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @EventListener({ApplicationReadyEvent.class, ApplicationFailedEvent.class})
    public void onSpringBootEvent(SpringApplicationEvent event) {
        System.out.println("@EventListener 监听到事件 : " + event.getClass().getSimpleName());
    }
}
