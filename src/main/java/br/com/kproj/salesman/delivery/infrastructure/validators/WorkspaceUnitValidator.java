package br.com.kproj.salesman.delivery.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class WorkspaceUnitValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;



    @Override
    public boolean supports(Class<?> clazz) {
        return WorkspaceUnit.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WorkspaceUnit workspaceUnit = (WorkspaceUnit) target;
        Set<ConstraintViolation<Object>> constraints = validator.validate(workspaceUnit);

        if (workspaceUnit.getSalesOrder() == null || workspaceUnit.getSalesOrder().isNew()) {
            errors.rejectValue("salesOrder", "act.delivery.sales.order.is.null");
        }

        if (workspaceUnit.getUser() == null || workspaceUnit.getUser().isNew()) {
            errors.rejectValue("salesOrder", "act.delivery.user.is.null");
        }

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
