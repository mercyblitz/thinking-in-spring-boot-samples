package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * {@link ObjectName} 模式匹配引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ObjectName
 * @since 1.0.0
 */
public class ObjectNamePatternMatchBootstrap {

    public static void main(String[] args) throws MalformedObjectNameException {
        ObjectName objectName = new ObjectName("thinking.in.springboot:name=小马哥,type=User,id=1");
        // 最模糊匹配
        patternMatch(objectName, "*:*");
        // 域模糊匹配
        patternMatch(objectName, "thinking*:*");
        patternMatch(objectName, "thinking.in.*:*");
        // 属性模糊匹配
        patternMatch(objectName, "thinking.in.springboot:*");
        patternMatch(objectName, "thinking.in.springboot:name=小马哥,*");
        patternMatch(objectName, "thinking.in.springboot:name=小马哥,type=User,*");
    }

    private static void patternMatch(ObjectName target, String pattern) throws MalformedObjectNameException {
        ObjectName matcher = new ObjectName(pattern);
        System.out.printf("ObjectName[%s] matches pattern[%s] : %s\n", target, pattern, matcher.apply(target));
    }
}
