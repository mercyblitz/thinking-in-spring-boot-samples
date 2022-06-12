package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.RequiredModelMBean;
import java.lang.management.ManagementFactory;

/**
 * {@link RequiredModelMBean} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see RequiredModelMBean
 * @since 1.0.0
 */
public class RequiredModelMBeanBootstrap {

    public static void main(String[] args) throws Exception {
        // 获取当前平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        RequiredModelMBean modelMBean = new RequiredModelMBean();
        modelMBean.setManagedResource(new User(), "objectReference");
        // 创建 RequiredModelMBean 的 ObjectName
        ObjectName objectName = new ObjectName("thinking.in.spring.boot.samples.production.ready.jmx:type=User");
        mBeanServer.registerMBean(modelMBean, objectName);
        System.out.printf("MBean (ObjectName[%s])已注册到平台 MBean Server...\n", objectName);
        System.out.println("按任意键结束...");
        System.in.read();
    }
}
