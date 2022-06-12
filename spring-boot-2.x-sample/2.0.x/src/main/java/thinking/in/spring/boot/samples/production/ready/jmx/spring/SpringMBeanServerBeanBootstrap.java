package thinking.in.spring.boot.samples.production.ready.jmx.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import java.lang.management.ManagementFactory;

/**
 * {@link MBeanServer} Bean 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MBeanServerFactory
 * @see MBeanServerFactoryBean
 * @since 1.0.0
 */
public class SpringMBeanServerBeanBootstrap {

    // 将 mBeanServer() 和 mBeanExporter() 方法顺序对调，观察输出的变化

    @Bean
    public MBeanServerFactoryBean mBeanServer() {
        MBeanServerFactoryBean factoryBean = new MBeanServerFactoryBean();
        return factoryBean;
    }

    @Bean
    public MBeanExporter mBeanExporter() {
        MBeanExporter exporter = new MBeanExporter();
        return exporter;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前 Class
        context.register(SpringMBeanServerBeanBootstrap.class);
        // 启动应用上下文
        context.refresh();
        // 获取名为 "mBeanExporter" Bean，来自于 mBeanExporter() 方法 @Bean 定义
        MBeanExporter mBeanExporter = context.getBean("mBeanExporter", MBeanExporter.class);
        // 获取名为 "mBeanServer" Bean，来自于 mBeanServer() 方法 @Bean 定义
        MBeanServer mBeanServer = context.getBean("mBeanServer", MBeanServer.class);
        // 从 "mBeanExporter" Bean 中获取来自于其afterPropertiesSet()方法创建 "mBeanServer" 对象
        MBeanServer mBeanServerFromMBeanExporter = mBeanExporter.getServer();
        System.out.printf("来自 mBeanExporter Bean 关联的 MBeanServer 实例是否等于平台 MBeanServer : %b \n",
                mBeanServerFromMBeanExporter == ManagementFactory.getPlatformMBeanServer()
        );

        System.out.printf("来自 mBeanExporter Bean 关联的 MBeanServer 实例是否等于 mBeanServer Bean : %b \n",
                mBeanServerFromMBeanExporter == mBeanServer
        );
        // 关闭应用上下文
        context.close();
    }
}
