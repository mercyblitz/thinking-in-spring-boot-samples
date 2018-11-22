package thinking.in.spring.boot.samples.autoconfigure.formatter;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link JsonFormatter} 单元测试
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see JsonFormatter
 * @since 1.0.0
 */
public class JsonFormatterTest {

    @Test
    public void testFormat() {
        JsonFormatter formatter = new JsonFormatter();
        Assert.assertEquals("{\"id\":1,\"name\":\"小马哥\"}", formatter.format(new User()));
    }

    private static class User {

        private long id = 1;

        private String name = "小马哥";

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
