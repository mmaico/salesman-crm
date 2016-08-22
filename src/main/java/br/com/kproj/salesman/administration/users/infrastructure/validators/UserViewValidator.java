package br.com.kproj.salesman.administration.users.infrastructure.validators;

import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableValidator;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class UserViewValidator implements UserValidator {

    private javax.validation.Validator validator;

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
        constraints.forEach(error -> errors.add(error.getMessage()));

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
