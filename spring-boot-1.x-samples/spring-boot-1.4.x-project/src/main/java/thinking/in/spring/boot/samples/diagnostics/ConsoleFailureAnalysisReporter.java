package thinking.in.spring.boot.samples.diagnostics;

import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalysisReporter;

/**
 * 自定义 {@link FailureAnalysisReporter}，控制台输出 {@link FailureAnalysis}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see FailureAnalysisReporter
 * @since 1.0.0
 */
public class ConsoleFailureAnalysisReporter implements FailureAnalysisReporter {

    @Override
    public void report(FailureAnalysis analysis) {
        System.out.printf("故障描述：%s \n执行动作：%s \n异常堆栈：%s \n",
                analysis.getDescription(),
                analysis.getAction(),
                analysis.getCause());
    }
}
