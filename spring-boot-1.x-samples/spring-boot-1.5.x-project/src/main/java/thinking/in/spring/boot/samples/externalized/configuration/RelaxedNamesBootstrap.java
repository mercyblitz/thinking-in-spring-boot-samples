package thinking.in.spring.boot.samples.externalized.configuration;

import org.springframework.boot.bind.RelaxedNames;

/**
 * {@link RelaxedNames} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class RelaxedNamesBootstrap {

    public static void main(String[] args) {
        // 生成属性名称 "user.HOME-PAGE" 的所有松散格式
        RelaxedNames relaxedNames = new RelaxedNames("user.homePage");
        // 输出所有松散格式
        relaxedNames.forEach(System.out::println);
    }
}