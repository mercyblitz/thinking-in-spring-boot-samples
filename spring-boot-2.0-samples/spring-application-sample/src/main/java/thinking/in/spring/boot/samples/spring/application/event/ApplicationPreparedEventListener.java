package thinking.in.spring.boot.samples.spring.application.event;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link ApplicationPreparedEvent} 事件监听器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ApplicationPreparedEvent
 * @see ApplicationListener
 * @see SpringApplicationRunListener#contextLoaded(ConfigurableApplicationContext)
 * @since 1.0.0
 */
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        // 获取 Spring 应用上下文
        ConfigurableApplicationContext context = event.getApplicationContext();
        // 调整 Spring 应用上下文的 ID
        context.setId("context-mercyblitz");
        System.out.println("当前 Spring 应用上下文 ID 调整为：" + context.getId());
    }
}
