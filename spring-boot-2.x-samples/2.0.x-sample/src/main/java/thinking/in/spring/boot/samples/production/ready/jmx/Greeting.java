package thinking.in.spring.boot.samples.production.ready.jmx;

/**
 * {@link GreetingMBean} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see GreetingMBean
 * @since 1.0.0
 */
public class Greeting implements GreetingMBean {
    @Override
    public String greet(String name) {
        return "Hello, " + name;
    }
}
