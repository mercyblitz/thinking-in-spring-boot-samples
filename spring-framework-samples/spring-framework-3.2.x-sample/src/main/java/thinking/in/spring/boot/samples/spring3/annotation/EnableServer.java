package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.context.annotation.Import;
import thinking.in.spring.boot.samples.spring3.server.Server;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 激活服务器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 * @see ServerImportSelector
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(ServerImportSelector.class) // 导入 ServerImportSelector
@Import(ServerImportBeanDefinitionRegistrar.class) // 替换 ServerImportSelector
public @interface EnableServer {

    /**
     * 设置服务器类型
     * @return non-null
     */
    Server.Type type();
}
