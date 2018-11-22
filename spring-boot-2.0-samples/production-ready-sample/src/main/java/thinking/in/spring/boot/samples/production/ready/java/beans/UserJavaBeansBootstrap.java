package thinking.in.spring.boot.samples.production.ready.java.beans;

import thinking.in.spring.boot.samples.production.ready.jmx.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

/**
 * {@link User} Java Beans 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Introspector
 * @since 1.0.0
 */
public class UserJavaBeansBootstrap {

    public static void main(String[] args) throws IntrospectionException {
        // 仅内省 User 类，排除 Object 的干扰
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        System.out.print("User 类属性名称：");
        // 输出所有的 PropertyDescriptor
        Stream.of(beanInfo.getPropertyDescriptors())
                // 获取属性名称
                .map(PropertyDescriptor::getName)
                .reduce((a, b) -> a + " , " + b)
                // 输出到控制台
                .ifPresent(System.out::println);
    }
}
