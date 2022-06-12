package thinking.in.spring.boot.samples.externalized.configuration.beans;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import thinking.in.spring.boot.samples.externalized.configuration.beans.propertyeditors.LocalDateEditor;

import java.time.LocalDate;

/**
 * {@link LocalDate} {@link PropertyEditorRegistrar} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class LocalDatePropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 为 LocalDate 类型属性注册 LocalDateEditor
        registry.registerCustomEditor(LocalDate.class, new LocalDateEditor());

    }
}
