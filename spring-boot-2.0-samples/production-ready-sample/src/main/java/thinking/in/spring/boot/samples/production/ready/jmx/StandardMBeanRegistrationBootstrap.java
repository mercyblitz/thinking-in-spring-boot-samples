package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 标准 MBean 注册引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ManagementFactory#getPlatformMBeanServer()
 * @since 1.0.0
 */
public class StandardMBeanRegistrationBootstrap {

    public static void main(String[] args) throws Exception {
        // 获取当前平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 创建 GreetingMBean 的 ObjectName
        ObjectName objectName = new ObjectName("thinking.in.spring.boot.samples.production.ready.jmx:type=Greeting");
        mBeanServer.registerMBean(new Greeting(), objectName);
        System.out.printf("MBean (ObjectName[%s])已注册到平台 MBean Server...\n", objectName);
        System.out.println("按任意键结束...");
        System.in.read();
    }
}
