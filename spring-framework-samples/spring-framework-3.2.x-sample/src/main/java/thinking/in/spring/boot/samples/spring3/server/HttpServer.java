package thinking.in.spring.boot.samples.spring3.server;

import org.springframework.stereotype.Component;

/**
 * HTTP 服务器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Component // 根据 ImportSelector 的契约，请确保是实现为 Spring 组件
public class HttpServer implements Server {

    @Override
    public void start() {
        System.out.println("HTTP 服务器启动中...");
    }

    @Override
    public void stop() {
        System.out.println("HTTP 服务器关闭中...");
    }
}
