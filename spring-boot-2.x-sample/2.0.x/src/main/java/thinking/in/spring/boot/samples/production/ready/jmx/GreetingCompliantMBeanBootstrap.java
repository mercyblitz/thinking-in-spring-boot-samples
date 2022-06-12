package thinking.in.spring.boot.samples.production.ready.jmx;

import com.sun.jmx.mbeanserver.Introspector;

import javax.management.NotCompliantMBeanException;

/**
 * 测试 {@link Greeting} MBean 兼容性的引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Greeting
 * @since 1.0.0
 */
public class GreetingCompliantMBeanBootstrap {

    public static void main(String[] args) throws NotCompliantMBeanException {
        // 测试 Greeting 的兼容性
        Introspector.checkCompliance(Greeting.class);
    }
}
