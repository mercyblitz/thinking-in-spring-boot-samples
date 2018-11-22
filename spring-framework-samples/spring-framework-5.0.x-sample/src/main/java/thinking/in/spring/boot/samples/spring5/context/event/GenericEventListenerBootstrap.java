package thinking.in.spring.boot.samples.spring5.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * 泛型 {@link ApplicationEvent 事件} 监听引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ApplicationEvent
 * @see ApplicationListener
 * @see EventListener
 * @see ResolvableType
 * @since 1.0.0
 */
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

public static class UserEventListener implements ApplicationListener<GenericEvent<User>> {

    @EventListener
    public void onUser(User user) {
        System.out.println("onUser : " + user);
    }

    @EventListener
    public void onUserEvent(GenericEvent<User> event) {
        System.out.println("onUserEvent : " + event.getSource());
    }

    @Override
    public void onApplicationEvent(GenericEvent<User> event) {
        System.out.println("onApplicationEvent : " + event.getSource());
    }
}

    /**
     * 用户实体类
     */
    public static class User {

        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "\'}";
        }
    }

    /**
     * 泛型事件
     *
     * @param <T> 泛型类型
     */
    public static class GenericEvent<T>
            extends ApplicationEvent implements ResolvableTypeProvider {

        public GenericEvent(T source) {
            super(source);
        }

        @Override
        public ResolvableType getResolvableType() {
            return ResolvableType.forClassWithGenerics(getClass(),
                    ResolvableType.forInstance(getSource()));
        }

        @Override
        public T getSource() {
            return (T) super.getSource();
        }
    }
}
