package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import thinking.in.spring.boot.samples.spring3.server.FtpServer;
import thinking.in.spring.boot.samples.spring3.server.HttpServer;
import thinking.in.spring.boot.samples.spring3.server.Server;

import java.util.Map;

/**
 * 服务器 {@link ImportSelector} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class ServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取 EnableServer 中的所有的属性方法，本例中仅有 type() 属性方法
        // 其中 key 为 属性方法的名称，value 为属性方法返回对象
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        // 获取名为"type" 的属相方法，并且强制转化成 Server.Type 类型
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        // 导入的类名称数组
        String[] importClassNames = new String[0];
        switch (type) {
            case HTTP: // 当设置 HTTP 服务器类型时，返回 HttpServer 组件
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            case FTP: //  当设置 FTP  服务器类型时，返回 FtpServer  组件
                importClassNames = new String[]{FtpServer.class.getName()};
                break;
        }
        return importClassNames;
    }
}
