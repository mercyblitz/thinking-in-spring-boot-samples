package thinking.in.spring.boot.samples.spring3.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thinking.in.spring.boot.samples.spring3.config.SpringContextConfiguration;
import thinking.in.spring.boot.samples.spring3.domain.User;

/**
 * Annotation 配置驱动引导程序
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class AnnotationConfigBootstrap {

	public static void main(String[] args) {
		// 构建 Annotation 配置驱动 Spring 上下文
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册 配置Bean SpringContextConfiguration 到 Spring 上下文
		context.register(SpringContextConfiguration.class);
		// 启动上下文
		context.refresh();
		// 获取名称为 "user" Bean 对象
		User user = context.getBean("user", User.class);
		// 输出用户名称："小马哥"
		System.out.printf("user.getName() = %s \n", user.getName());
		// 关闭 Spring 上下文
		context.close();
	}
}