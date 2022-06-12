package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;

/**
 * {@link ConfigurationPropertyName} 等价性 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ConfigurationPropertyNameEqualityBootstrap {

    public static void main(String[] args) {
//        ConfigurationPropertyName one = ConfigurationPropertyName.of("user.home-page");
//        ConfigurationPropertyName aonther = ConfigurationPropertyName.of("user.homepage");
//        System.out.printf("配置属性名['%s'] 与 配置属性名['%s'] 相等 : %b", one, aonther, one.equals(aonther));

        // 错误配置属性名，仅允许小写字母 "a-z"、"0-9" 以及 "-"(横划线)
        ConfigurationPropertyName.of("user.HOMEP-AGE");
    }
}
