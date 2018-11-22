package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.context.annotation.Import;
import thinking.in.spring.boot.samples.spring3.config.HelloWorldConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HelloWorld 模块激活模式 Annotation
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 * @see HelloWorldConfiguration
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class) // 导入 HelloWorldConfiguration
public @interface EnableHelloWorld {
}
