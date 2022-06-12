package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

/**
 * {@link DataBinder} 示例 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class DataBinderBootstrap {

    public static void main(String[] args) {

        Integer integer = new Integer(0);

        DataBinder dataBinder = new DataBinder(integer);
        dataBinder.setIgnoreUnknownFields(false);
        dataBinder.setIgnoreInvalidFields(false);

        MutablePropertyValues propertyValues = new MutablePropertyValues();

        propertyValues.addPropertyValue("value", 2);

        dataBinder.bind(propertyValues);

        System.out.println(integer);

    }
}