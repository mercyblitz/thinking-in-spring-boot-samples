package thinking.in.spring.boot.samples.externalized.configuration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import thinking.in.spring.boot.samples.api.domain.User;

/**
 * {@link User 用户} 所在{@link User.City 城市}名称非空校验
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class UserCityNameNotEmptyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz); // 仅校验 User 类
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "city.name", "","用户的城市名称不能为空");
    }
}
