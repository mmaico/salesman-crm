package br.com.kproj.salesman.register.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EntityIDValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Boolean.TRUE;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Identifiable identifiable = (Identifiable) target;

        if (identifiable == null || identifiable.isNew()) {
            errors.reject("entity.without.id.on.relation");
        }

    }


}
