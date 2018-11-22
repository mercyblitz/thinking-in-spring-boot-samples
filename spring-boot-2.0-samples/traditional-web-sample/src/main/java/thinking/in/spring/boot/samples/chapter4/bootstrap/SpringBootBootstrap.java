package thinking.in.spring.boot.samples.chapter4.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thinking.in.spring.boot.samples.chapter4.traditional.web.controller.HelloWorldRestController;
import thinking.in.spring.boot.samples.chapter4.traditional.web.servlet.JspFilter;

/**
 * Spring Boot 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@SpringBootApplication(
        scanBasePackageClasses = {             // 扫描 HelloWorldRestController 和 JspFilter 下的 package
                HelloWorldRestController.class,// HelloWorldRestController 注解 @RestController
                JspFilter.class                // JspFilter 注解 @WebFilter
        }
)
public class SpringBootBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBootstrap.class, args);
    }
}
