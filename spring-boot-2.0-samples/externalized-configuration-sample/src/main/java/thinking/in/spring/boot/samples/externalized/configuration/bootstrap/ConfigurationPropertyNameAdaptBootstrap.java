package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * {@link ConfigurationPropertyName#adapt(CharSequence, char)} 方法作用引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ConfigurationPropertyNameAdaptBootstrap {

    public static void main(String[] args) throws Exception {
        // 查找 adapt(CharSequence, char) 静态方法
        Method method = ReflectionUtils.findMethod(ConfigurationPropertyName.class, "adapt", CharSequence.class, char.class);
        // 设置非 public 可访问
        method.setAccessible(true);
        // 执行 adapt 静态方法
        ConfigurationPropertyName configurationPropertyName = (ConfigurationPropertyName)
                method.invoke(null, "user.HOMEP-AGE", '.');
        // 获取 "." 分割后的元素数量
        int numberOfElements = configurationPropertyName.getNumberOfElements();
        for (int i = 0; i < numberOfElements; i++) {
            // 原始格式
            String originalElement = configurationPropertyName.getElement(i, ConfigurationPropertyName.Form.ORIGINAL);
            // 统一格式
            String uniformElement = configurationPropertyName.getElement(i, ConfigurationPropertyName.Form.UNIFORM);
            // 输出
            System.out.printf("配置属性名['%s']的元素[%d] 原始格式 : '%s' , 统一格式 : '%s' \n",
                    configurationPropertyName, i, originalElement, uniformElement);
        }
    }
}
