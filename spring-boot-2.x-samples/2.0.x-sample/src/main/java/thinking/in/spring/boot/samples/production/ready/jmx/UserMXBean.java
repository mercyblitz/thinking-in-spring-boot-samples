package thinking.in.spring.boot.samples.production.ready.jmx;

import javax.management.MXBean;

/**
 * 用户 {@link MXBean}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see MXBean
 * @since 1.0.0
 */
@MXBean
public interface UserMXBean {

    Long getId();

    void setId(Long id);

    Name getName();

    void setName(Name name);

    class Name {

        private String firstName;

        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    }
}
