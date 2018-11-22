package thinking.in.spring.boot.samples.chapter4.traditional.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorld {@link RestController} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
public class HelloWorldRestController {

    @GetMapping("/hello-world-rest-controller")
    public String helloworld() {
        return "HelloWorld";
    }

}
