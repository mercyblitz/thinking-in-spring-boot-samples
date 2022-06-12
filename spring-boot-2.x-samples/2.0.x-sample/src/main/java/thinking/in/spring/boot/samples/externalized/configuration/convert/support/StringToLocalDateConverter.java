package thinking.in.spring.boot.samples.externalized.configuration.convert.support;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * {@link String} 到 {@link LocalDate} {@link Converter 转换器}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source);
    }
}