package thinking.in.spring.boot.samples.production.ready.actuator.management;

import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * 自定义 {@link @ManagementContextConfiguration ManagementContextConfiguration} 配置类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ManagementContextConfiguration
 * @since 1.0.0
 */
@ManagementContextConfiguration
public class CustomizedManagementContextConfiguration {

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshed(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        ApplicationContext parentContext = context.getParent();
        System.out.printf("当前管理端应用上下文 ID：%s，其双亲应用上下文 ID：%s\n",
                context.getId(),
                parentContext == null ? null : parentContext.getId()
        );
    }
}
