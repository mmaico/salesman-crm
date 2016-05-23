package br.com.kproj.salesman.incidents.infrastructure;

import br.com.kproj.salesman.infrastructure.entity.Incident;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class IncidentValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Incident.class.equals(clazz.getSuperclass());
    }

    @Override
    public void validate(Object target, Errors errors) {
        Incident incident = (Incident) target;
        Set<ConstraintViolation<Object>> constraints = validator.validate(incident);

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
