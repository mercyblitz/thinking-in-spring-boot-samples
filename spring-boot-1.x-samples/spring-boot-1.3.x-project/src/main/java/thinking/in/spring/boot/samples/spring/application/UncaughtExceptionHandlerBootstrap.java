package thinking.in.spring.boot.samples.spring.application;

/**
 * {@link Thread.UncaughtExceptionHandler} 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Thread.UncaughtExceptionHandler
 * @since 1.0.0
 */
public class UncaughtExceptionHandlerBootstrap {

    public static void main(String[] args) {
        // 获取当前线程 - main
        Thread mainThread = Thread.currentThread();
        System.out.printf("当前执行线程 %s!\n", mainThread.getName());
        // 为 main 线程设置 UncaughtExceptionHandler 实现
        mainThread.setUncaughtExceptionHandler((thread, throwable) -> {
            System.out.printf("处理线程[%s]的非捕获异常，详情：%s\n"
                    , thread.getName(), throwable.getMessage());
            System.exit(0);
        });
        // 抛出异常
        throw new RuntimeException("故意抛出异常，测试 UncaughtExceptionHandler 是否处理！");
    }
}
