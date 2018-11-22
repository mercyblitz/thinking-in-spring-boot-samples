package thinking.in.spring.boot.samples.diagnostics;

import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;

/**
 * {@link UnknownError} 自定义 {@link FailureAnalyzer 实现}，分析并生成 {@link FailureAnalysis}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see FailureAnalyzer
 * @since 1.0.0
 */
public class UnknownErrorFailureAnalyzer implements FailureAnalyzer {

    @Override
    public FailureAnalysis analyze(Throwable failure) {
        if (failure instanceof UnknownError) { // 判断上游异常类型判断
            return new FailureAnalysis("未知错误", "请重启尝试", failure);
        }
        return null;
    }
}
