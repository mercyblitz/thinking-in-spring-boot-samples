package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MBeanServer;
import javax.management.MXBean;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.ManagementFactory;

/**
 * {@link UserMXBean} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MXBean
 * @since 1.0.0
 */
public class UserMXBeanBootstrap {

    public static void main(String[] args) throws Exception {
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserMXBean 定义 ObjectName
        ObjectName objectName = new ObjectName("thinking.in.spring.boot.samples.production.ready.jmx:type=User");
        // 创建 UserMXBean 实例
        mBeanServer.registerMBean(createUserMXBean(), objectName);
        // 获取 id 属性
//        Long id = (Long) mBeanServer.getAttribute(objectName, "id"); // 该语句将引发异常，故注释之
        Long id = (Long) mBeanServer.getAttribute(objectName, "Id");
        // 输出 id 属性
        System.out.println("id 属性 : " + id);
        // 获取 属性 Name 并强转 CompositeData 类型
        CompositeData compositeData = (CompositeData) mBeanServer.getAttribute(objectName, "Name");
        System.out.printf("Name 属性 - firstName : %s , lastName : %s \n",
                compositeData.get("firstName"),
                compositeData.get("lastName"));
    }

    private static UserMXBean createUserMXBean() {
        UserMXBean user = new User();
        user.setId(1L);
        UserMXBean.Name name = new UserMXBean.Name();
        name.setFirstName("Mercy");
        name.setLastName("Ma");
        user.setName(name);
        return user;
    }
}