package thinking.in.spring.boot.samples.production.ready.jmx;

/**
 * {@link UserMXBean} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see UserMXBean
 * @since 1.0.0
 */
public class User implements UserMXBean {

    private Long id;

    private Name name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public void setName(Name name) {
        this.name = name;
    }
}
