package thinking.in.spring.boot.samples.production.ready.jmx.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring {@link MBeanExporter} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MBeanServerFactory
 * @see MBeanServerFactoryBean
 * @since 1.0.0
 */
public class SpringMBeanExporterBootstrap {

    @Bean
    public Calculator calculator() {
        Calculator calculator = new Calculator();
        calculator.setVersion("BETA");
        return calculator;
    }

    @Bean
    public MBeanExporter mBeanExporter(Calculator calculator) {
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("production.ready.jmx.spring:name=Calculator", calculator);
        exporter.setBeans(beans);
        return exporter;
    }

    public static void main(String[] args) throws IOException {
        // 创造 MBeanServer 对象
        MBeanServer mBeanServerFromCreate = MBeanServerFactory.createMBeanServer();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 SpringMBeanExporterBootstrap
        context.register(SpringMBeanExporterBootstrap.class);
        // 启动应用上下文
        context.refresh();
        // 获取 "mBeanExporter" Bean
        MBeanExporter mBeanExporter = context.getBean("mBeanExporter", MBeanExporter.class);
        // 从 "mBeanExporter" Bean 获取 MBeanServer
        MBeanServer mBeanServerFromBean = mBeanExporter.getServer();
        // 比较 mBeanServerFromBean 是否与平台 MBeanServer 相同
        System.out.printf("来自于 MBeanExporter Bean 的 MBeanServer 是否相当于 ManagementFactory.getPlatformMBeanServer() : %b\n",
                mBeanServerFromBean == ManagementFactory.getPlatformMBeanServer());
        System.out.printf("来自于 MBeanExporter Bean 的 MBeanServer 是否相当于 ManagementFactory.createMBeanServer() : %b\n",
                mBeanServerFromBean == mBeanServerFromCreate);
        System.out.println("按任意键结束...");
        System.in.read();
        // 关闭应用上下文
        context.close();
    }
}


//     // 在 MBeanExporter Bean 初始化之前提前构建 MBeanServer
//        MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();