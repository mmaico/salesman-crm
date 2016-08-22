package br.com.kproj.salesman.accounts.infrastructure.validators;

import br.com.kproj.salesman.accounts.domain.model.address.Address;
import br.com.kproj.salesman.accounts.domain.model.address.AddressValidator;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class AddressViewValidator implements AddressValidator {

    private javax.validation.Validator validator;

    public AddressViewValidator() {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }

    @Override
    public void checkRules(Address address) {
        Set<String> errors = Sets.newHashSet();

        Set<ConstraintViolation<Object>> constraints = validator.validate(address);
        constraints.forEach(error -> errors.add(error.getMessage()));

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
