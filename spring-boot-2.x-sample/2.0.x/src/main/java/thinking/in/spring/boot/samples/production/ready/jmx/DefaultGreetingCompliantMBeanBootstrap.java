package thinking.in.spring.boot.samples.production.ready.jmx;

import com.sun.jmx.mbeanserver.Introspector;

import javax.management.NotCompliantMBeanException;

/**
 * 测试 {@link DefaultGreeting} MBean 兼容性的引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see DefaultGreeting
 * @since 1.0.0
 */
public class DefaultGreetingCompliantMBeanBootstrap {

    public static void main(String[] args) throws NotCompliantMBeanException {
        // 测试 DefaultGreeting 的兼容性
        Introspector.checkCompliance(DefaultGreeting.class);
    }
}
