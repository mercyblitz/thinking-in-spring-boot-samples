package thinking.in.spring.boot.samples.externalized.configuration.convert.support;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

/**
 * {@link String} 到 {@link LocalDate} {@link GenericConverter 转换器}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class StringToLocalDateGenericConverter implements GenericConverter {

    private final StringToLocalDateConverter delegate;

    /**
     * @param delegate {@link StringToLocalDateConverter} 实现代理
     */
    public StringToLocalDateGenericConverter(StringToLocalDateConverter delegate) {
        this.delegate = delegate;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, LocalDate.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (String.class.equals(sourceType.getObjectType())
                && LocalDate.class.equals(targetType.getObjectType())) {

            System.out.println("执行 StringToLocalDateGenericConverter#convert()");

            return delegate.convert((String) source);

        }
        return source;
    }
}
