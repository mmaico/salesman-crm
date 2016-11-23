package br.com.kproj.salesman.accounts.customers.infrastructure.validators;

import br.com.kproj.salesman.accounts.customers.domain.model.customer.Customer;
import br.com.kproj.salesman.accounts.customers.domain.model.customer.CustomerValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class AccountViewValidator implements CustomerValidator {

    private javax.validation.Validator validator;

    public AccountViewValidator() {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }

    @Override
    public void checkRules(Customer account) {
        Set<String> errors = Sets.newHashSet();

        Set<ConstraintViolation<Object>> constraints = validator.validate(account);
        constraints.forEach(error -> errors.add(error.getMessage()));

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
