package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * {@link ObjectName} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ObjectName
 * @since 1.0.0
 */
public class ObjectNameBootstrap {

    public static void main(String[] args) throws MalformedObjectNameException {
        ObjectName objectName = new ObjectName("thinking.in.springboot:name=小马哥,type=User,id=1");
        System.out.printf("Object[%s] 域名（Domain）为 : %s\n", objectName, objectName.getDomain());
        System.out.printf("Object[%s] 键属性（Key Properties）列表为 : %s\n", objectName, objectName.getKeyPropertyList());
    }
}
