package br.com.kproj.salesman.business_prospecting.leads;

import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class LeadValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Lead.class.equals(clazz.getSuperclass());
    }

    @Override
    public void validate(Object target, Errors errors) {
        Lead lead = (Lead) target;
        Set<ConstraintViolation<Object>> constraints = validator.validate(lead);

        constraints.forEach(error ->
                errors.rejectValue(error.getPropertyPath().toString(), error.getMessage()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }
}
