package thinking.in.spring.boot.samples.externalized.configuration.bootstrap;

import org.springframework.boot.context.properties.bind.Bindable;

import java.util.Arrays;

/**
 * {@link Bindable} 示例 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class BindableBootstrap {

    public static void main(String[] args) {
        // Fluent API 构建新 Bindable 对象（不变）
        Bindable bindable = Bindable.of(int.class)
                .withExistingValue(1)
                .withAnnotations();
        echo(bindable);
    }

    private static void echo(Bindable<?> bindable) {
        System.out.printf("Bindable type = %s , boxedType = %s , value = %s , annotations = %s\n",
                bindable.getType(), bindable.getBoxedType(), bindable.getValue().get(), Arrays.asList(bindable.getAnnotations()));
    }
}
