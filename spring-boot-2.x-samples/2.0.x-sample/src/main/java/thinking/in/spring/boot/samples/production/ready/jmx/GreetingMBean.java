package thinking.in.spring.boot.samples.production.ready.jmx;

/**
 * Greeting 标准 MBean
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 1.0.0
 */
public interface GreetingMBean {

    /**
     * 问候某人
     *
     * @param name 某人姓名
     * @return 给某人的问候语
     */
    String greet(String name);
}
