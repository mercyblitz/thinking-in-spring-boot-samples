package thinking.in.spring.boot.samples.spring.application.bootstrap;

/**
 * {@link System#exit(int) JVM 退出方法} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see System#exit(int)
 * @see Runtime#exit(int)
 * @since 1.0.0
 */
public class SystemExitBootstrap {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        // 注册 Shutdown Hook

        runtime.addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown Hook 执行...");
        }));

        runtime.runFinalization();

        System.exit(0);

    }


    @Override
    public void finalize() {
        System.out.println("finalize 执行...");
    }
}
