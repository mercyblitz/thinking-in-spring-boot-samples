package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;

/**
 * {@link ConfigurationPropertyName} 层次性 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ConfigurationPropertyName
 * @since 1.0.0
 */
public class ConfigurationPropertyNameHierarchyBootstrap {

    public static void main(String[] args) {
        // 配置属性名抽象：ConfigurationPropertyName
        ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.of("a.b.c");
        // 获取 "." 分割后的元素数量
        int numberOfElements = configurationPropertyName.getNumberOfElements();
        for (int i = 0; i < numberOfElements; i++) {
            // 获取劈断大小
            int size = i + 1;
            ConfigurationPropertyName chopped = configurationPropertyName.chop(size);
            // 输出
            System.out.printf("劈断[大小 : %d]的配置属性名['%s'] 是否为配置属性名['%s'] 的父属性名 : %b , 祖属性名 : %b \n",
                    size, chopped, configurationPropertyName, chopped.isParentOf(configurationPropertyName),
                    chopped.isAncestorOf(configurationPropertyName)
            );
        }
    }
}