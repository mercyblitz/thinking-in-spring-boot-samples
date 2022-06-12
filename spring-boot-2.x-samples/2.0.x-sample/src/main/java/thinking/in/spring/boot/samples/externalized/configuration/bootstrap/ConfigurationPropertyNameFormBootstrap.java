package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;

/**
 * {@link ConfigurationPropertyName} {@link ConfigurationPropertyName.Form} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ConfigurationPropertyName
 * @since 1.0.0
 */
public class ConfigurationPropertyNameFormBootstrap {

    public static void main(String[] args) {
        displayElementForm("user.home-page");
        displayElementForm("user.homepage");
    }

    private static void displayElementForm(String propertyName) {
        // 配置属性名抽象：ConfigurationPropertyName
        ConfigurationPropertyName configurationPropertyName = ConfigurationPropertyName.of(propertyName);
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