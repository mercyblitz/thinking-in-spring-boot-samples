package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link MapConfigurationPropertySource} 实例 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MapConfigurationPropertySource
 * @since 1.0.0
 */
public class MapConfigurationPropertySourceBootstrap {

    public static void main(String[] args) {

        MapConfigurationPropertySource propertySource = new MapConfigurationPropertySource();

        propertySource.put("userName", "Mercy");
        propertySource.put("user-id", 1);
        propertySource.put("user_id", 1);

        propertySource.stream().map(name -> name.getLastElement(ConfigurationPropertyName.Form.UNIFORM))
                .forEach(System.out::println);
    }
}
