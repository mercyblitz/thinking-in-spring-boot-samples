package thinking.in.spring.boot.samples.spring5.context.event;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 监听自定义事件引导类，通过 {@link GenericApplicationContext#registerBean(Class, BeanDefinitionCustomizer...)} 方法注册
 * {@link ApplicationListener} Bean
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see GenericApplicationContext
 * @see ApplicationEvent
 * @see ApplicationListener
 * @since 1.0.0
 */
public class ApplicationListenerBeanOnCustomEventBootstrap {

    public static void main(String[] args) {
        // 创建 Spring 应用上下文 GenericApplicationContext
        GenericApplicationContext context = new GenericApplicationContext();
        // 注册 ApplicationListener<MyApplicationEvent> 实现 MyApplicationListener
        context.registerBean(MyApplicationListener.class); // registerBean 方法从 Spring 5 引入
        // 初始化上下文
        context.refresh();
        // 发布自定义事件 MyApplicationEvent
        context.publishEvent(new MyApplicationEvent("Hello World"));
        // 关闭上下文
        context.close();
        // 再次发布事件
        context.publishEvent(new MyApplicationEvent("Hello World Again"));
    }

    public static class MyApplicationEvent extends ApplicationEvent {

        public MyApplicationEvent(String source) {
            super(source);
        }
    }

    public static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

        @Override
        public void onApplicationEvent(MyApplicationEvent event) {
            System.out.println(event.getClass().getSimpleName());
        }
    }
}
