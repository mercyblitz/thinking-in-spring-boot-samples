package thinking.in.spring.boot.samples.production.ready.jmx.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;

import java.io.IOException;

/**
 * Spring JMX 注解驱动引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MBeanExporter
 * @see MetadataMBeanInfoAssembler
 * @see AnnotationJmxAttributeSource
 * @since 1.0.0
 */
public class SpringJmxAnnotationBootstrap {

    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public AnnotationMBeanExporter mBeanExporter() {
        return new AnnotationMBeanExporter();
    }

//    使用 AnnotationMBeanExporter 替换以下实现：
//
//    @Bean
//    public MBeanExporter exporter(MBeanInfoAssembler assembler, MetadataNamingStrategy namingStrategy) {
//        MBeanExporter mBeanExporter = new MBeanExporter();
//        mBeanExporter.setAssembler(assembler);
//        mBeanExporter.setNamingStrategy(namingStrategy);
//        mBeanExporter.setAutodetect(true);
//        return mBeanExporter;
//    }
//
//    @Bean
//    public MBeanInfoAssembler assembler(JmxAttributeSource jmxAttributeSource) {
//        return new MetadataMBeanInfoAssembler(jmxAttributeSource);
//    }
//
//    @Bean
//    public JmxAttributeSource jmxAttributeSource() {
//        return new AnnotationJmxAttributeSource();
//    }
//
//    @Bean
//    public MetadataNamingStrategy namingStrategy(JmxAttributeSource jmxAttributeSource) {
//        return new MetadataNamingStrategy(jmxAttributeSource);
//    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 SpringJmxAnnotationBootstrap
        context.register(SpringJmxAnnotationBootstrap.class);
        // 启动应用上下文
        context.refresh();
        System.out.println("按任意键结束...");
        System.in.read();
        // 关闭应用上下文
        context.close();
    }
}
