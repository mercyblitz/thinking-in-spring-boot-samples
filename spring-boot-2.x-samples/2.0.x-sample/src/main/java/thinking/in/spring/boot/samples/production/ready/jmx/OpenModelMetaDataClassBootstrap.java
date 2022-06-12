package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.StandardMBean;
import javax.management.openmbean.OpenType;

/**
 * {@link OpenType 开放}模型 元数据 Class 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see OpenType
 * @since 1.0.0
 */
public class OpenModelMetaDataClassBootstrap {

    public static void main(String[] args) {
        StandardMBean standardMBean = new StandardMBean(new User(), UserMXBean.class, true);
        System.out.println(standardMBean.getMBeanInfo());
    }
}
