package thinking.in.spring.boot.samples.api.domain;

import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Map;

/**
 * 用户 Domain
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class User {

    /**
     *
     */
    private Long id;

    private String name;

    private Short age;

    private LocalDate birthday;

    @Valid
    private City city;

    private String homePage;

    private String gitHub;

    private Map<Currency, Double> money;

    private List<String> banks;

    public static class City {

        @NotEmpty
        String name;

        String postCode;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        @Override
        public String toString() {
            return "City{" + "name='" + name + '\'' + ", postCode='" + postCode + '\'' + '}';
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Short age) {
        this.age = age;

    }

    public Short getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Gets City object
     * @return
     */
    public City getCity() {
        return city;
    }

    @DeprecatedConfigurationProperty(reason = "不推荐使用 String 类型描述用户主页")
    @Deprecated
    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public Map<Currency, Double> getMoney() {
        return money;
    }

    public void setMoney(Map<Currency, Double> money) {
        this.money = money;
    }

    public List<String> getBanks() {
        return banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", city=" + city +
                ", homePage='" + homePage + '\'' +
                ", gitHub='" + gitHub + '\'' +
                ", money=" + money +
                ", banks=" + banks +
                '}';
    }
}