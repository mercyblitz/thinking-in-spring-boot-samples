package thinking.in.spring.boot.samples.spring25.repository;


import thinking.in.spring.boot.samples.spring25.annotation.StringRepository;

import java.util.Arrays;
import java.util.List;

/**
 * 名字仓储，Bean 名称为 "chineseNameRepository"
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@StringRepository("chineseNameRepository")
public class NameRepository {

    /**
     * 查找所有的名字
     *
     * @return non-null List
     */
    public List<String> findAll() {
        return Arrays.asList("张三", "李四", "小马哥");
    }
}
