package thinking.in.spring.boot.samples.spring3.service;

/**
 * 计算服务
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CalculatingService {

    /**
     * 累加求和
     * @param values 多个累加值
     * @return 累加结果
     */
    Integer sum(Integer... values);

}
