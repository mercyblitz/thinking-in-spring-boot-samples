package thinking.in.spring.boot.samples.production.ready.jmx;

import com.sun.jmx.mbeanserver.Introspector;

import javax.management.MBeanInfo;
import javax.management.NotCompliantMBeanException;
import java.util.Arrays;

/**
 * {@link GreetingMBean} 内省引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 1.0.0
 */
public class GreetingMBeanIntrospectionBootstrap {

    public static void main(String[] args) throws NotCompliantMBeanException {
        // 获取 Greeting 的 MBeanInfo
        MBeanInfo mBeanInfo = Introspector.testCompliance(Greeting.class);
        printf("Greeting 的 MBeanInfo 详情：");
        printf("MBeanAttributeInfo[]：%s", Arrays.asList(mBeanInfo.getAttributes()));
        printf("MBeanOperationInfo[]：%s", Arrays.asList(mBeanInfo.getOperations()));
        printf("MBeanConstructorInfo[]：%s", Arrays.asList(mBeanInfo.getConstructors()));
        printf("MBeanNotificationInfo[]：%s", Arrays.asList(mBeanInfo.getNotifications()));
    }

    private static void printf(String message, Object... args) {
        System.out.printf(message + "\n", args);
    }
}
