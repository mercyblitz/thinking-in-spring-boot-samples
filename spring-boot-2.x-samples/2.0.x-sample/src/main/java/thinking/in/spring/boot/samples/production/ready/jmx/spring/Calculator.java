package thinking.in.spring.boot.samples.production.ready.jmx.spring;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * 计算器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@ManagedResource(
        objectName = "production.ready.jmx.spring:name=Calculator",
        description = "计算器 ModelMBean"
)
public class Calculator {

    /**
     * 计算器实现版本
     */
    private String version;

    @ManagedOperation(description = "加法操作")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "a", description = "加数"),
            @ManagedOperationParameter(name = "b", description = "被加数")})
    public int add(int a, int b) {
        return a + b;
    }

    public String getVersion() {
        return version;
    }

    @ManagedAttribute(description = "计算器实现版本", defaultValue = "BETA")
    public void setVersion(String version) {
        this.version = version;
    }
}
