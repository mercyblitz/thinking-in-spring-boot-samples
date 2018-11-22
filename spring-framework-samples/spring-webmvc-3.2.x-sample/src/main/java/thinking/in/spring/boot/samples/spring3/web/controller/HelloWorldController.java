package thinking.in.spring.boot.samples.spring3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello World {@link Controller}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
public class HelloWorldController {

    @RequestMapping
    @ResponseBody
    public String hellloWorld() {
        return "Hello,World!!!";
    }
}
