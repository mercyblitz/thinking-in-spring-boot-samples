package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.MBeanInfo;
import javax.management.openmbean.OpenMBeanConstructorInfo;
import javax.management.openmbean.OpenMBeanConstructorInfoSupport;
import javax.management.openmbean.OpenMBeanInfoSupport;
import javax.management.openmbean.OpenMBeanOperationInfo;
import javax.management.openmbean.OpenMBeanOperationInfoSupport;
import javax.management.openmbean.OpenMBeanParameterInfo;
import javax.management.openmbean.OpenMBeanParameterInfoSupport;
import javax.management.openmbean.SimpleType;

import java.util.Arrays;

import static javax.management.MBeanOperationInfo.ACTION;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see DynamicMBean
 * @since 1.0.0
 */
public class GreetingOpenMBean implements DynamicMBean {
    /**
     * 代理执行对象
     */
    private final Greeting delegate = new Greeting();
    /**
     * 操作方法名称："greet"
     */
    public static final String GREET_METHOD_NAME = "greet";

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) {
        System.out.printf("signature 参数: %s\n", Arrays.asList(signature));
        // 匹配 "greet" 方法
        if (GREET_METHOD_NAME.equals(actionName) && params.length == 1) {
            return delegate.greet((String) params[0]);
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return new OpenMBeanInfoSupport(
                Greeting.class.getName(),            // OpenMBean 类名称
                "Greeting OpenMBean 信息", // OpenMBean 描述信息
                of(),                                // OpenMBeanAttributeInfo[] : 无属性信息
                of(defaultOpenConstructorInfo()),    // OpenMBeanConstructorInfo[] : 默认构造器
                of(greetOpenOperationInfo()),        // OpenMBeanOperationInfo[] : greet(String) 方法
                of()                                 // MBeanNotificationInfo[] : 无通知信息
        );
    }

    private OpenMBeanOperationInfo greetOpenOperationInfo() {
        return new OpenMBeanOperationInfoSupport(
                GREET_METHOD_NAME,                // 操作方法名称
                "greet 方法",           // 操作方法描述
                of(nameOpenMBeanParameterInfo()), // 操作方法参数信息
                SimpleType.STRING,                // 操作方法返回类型
                ACTION);
    }

    private OpenMBeanParameterInfo nameOpenMBeanParameterInfo() {
        return new OpenMBeanParameterInfoSupport(
                "name",             // 参数名称
                "name 参数",    // 参数描述信息
                SimpleType.STRING);       // 参数类型
    }

    private OpenMBeanConstructorInfo defaultOpenConstructorInfo() {
        return new OpenMBeanConstructorInfoSupport(
                "Greeting",      // 构造器名称
                "默认构造器", // 构造器描述
                of());                 // 构造器参数
    }

    // of 方法 - 简化数组创建
    private static <T> T[] of(T... objects) {
        return objects;
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException {
        throw new AttributeNotFoundException("No Attribute");
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException {
        throw new AttributeNotFoundException("No Attribute");
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        return new AttributeList();
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return attributes;
    }
}
