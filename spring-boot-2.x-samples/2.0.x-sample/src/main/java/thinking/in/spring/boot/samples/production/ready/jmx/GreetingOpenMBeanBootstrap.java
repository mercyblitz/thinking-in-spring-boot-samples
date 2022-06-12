package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * {@link GreetingOpenMBean} 开放 MBean 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Greeting
 * @since 1.0.0
 */
public class GreetingOpenMBeanBootstrap {

    public static void main(String[] args) throws Exception {
        // 获取当前平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 创建 GreetingMBean 的 ObjectName
        ObjectName objectName = new ObjectName("thinking.in.spring.boot.samples.production.ready.jmx:type=Greeting");
        mBeanServer.registerMBean(new GreetingOpenMBean(), objectName);
        Object[] params = of("World");
        System.out.printf("OpenMBean(ObjectName[%s])已注册到平台 MBean Server...\n", objectName);
        Object returnValue = mBeanServer.invoke(objectName, "greet", params, of());
        System.out.printf("OpenMBean(ObjectName[%s]) greet(\"%s\") 方法执行结果: %s\n", objectName, params[0], returnValue);
        System.out.println("按任意键结束...");
        System.in.read();
    }

    // of 方法 - 简化数组创建
    private static <T> T[] of(T... objects) {
        return objects;
    }
}
