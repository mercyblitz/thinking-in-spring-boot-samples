package thinking.in.spring.boot.samples.externalized.configuration.beans.propertyeditors;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

/**
 * {@link LocalDate} {@link PropertyEditor} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class LocalDateEditor extends PropertyEditorSupport {
    public void setAsText(String value) {
        System.out.println("执行 LocalDateEditor#setAsText()");
        // 格式: YYYY-MM-DD
        LocalDate localDate = LocalDate.parse(value);
        setValue(localDate);
    }


}
