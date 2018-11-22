package thinking.in.spring.boot.samples.spring3.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Lambda {@link CalculatingService} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@Profile("Java8")
public class LambdaCalculatingService implements CalculatingService {

    @Override
    public Integer sum(Integer... values) {

        int sum =  Stream.of(values).reduce(0, Integer::sum);

        System.out.printf("[Java 8 Lambda实现] %s 累加结果 : %d\n", Arrays.asList(values), sum);

        return sum;
    }
}
