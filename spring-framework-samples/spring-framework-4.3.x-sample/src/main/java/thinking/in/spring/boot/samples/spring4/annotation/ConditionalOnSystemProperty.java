package thinking.in.spring.boot.samples.spring4.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 指定系统{@link #name() 属性名称}与{@link #value() 值}匹配条件注解
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 * @see OnSystemPropertyCondition
 */
@Target({ElementType.METHOD}) // 只能标注在方法上面
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    /**
     * @return System 属性名称
     */
    String name();

    /**
     * @return System 属性值
     */
    String value();

}
