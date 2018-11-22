package thinking.in.spring.boot.samples.spring3.server;

/**
 * 服务器接口
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Server {

    /**
     * 启动服务器
     */
    void start();

    /**
     * 关闭服务器
     */
    void stop();

    /**
     * 服务器类型
     */
    enum Type {

        HTTP, // HTTP 服务器
        FTP   // FTP  服务器
    }
}
