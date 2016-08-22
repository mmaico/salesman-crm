package br.com.kproj.salesman.administration.users.view.validators;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isBlank;

@Component
public class UserViewValidator implements UserValidator {

    private javax.validation.Validator validator;

    @Autowired
    private AvatarValidator avatarValidator;

    public UserViewValidator() {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }

    @Override
    public void checkRules(User user) {
        Set<String> errors = Sets.newHashSet();

        Set<ConstraintViolation<Object>> constraints = validator.validate(user);

        if (user.isNew()) {
            if (isBlank(user.getPassword()) || isBlank(user.getPasswordConfirm())
                    || !user.getPassword().equals(user.getPasswordConfirm()))
                errors.add("user.invalid.password");
        }

        avatarValidator.validate(user.getAvatar(), errors);

        constraints.forEach(error -> errors.add(error.getMessage()));

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
