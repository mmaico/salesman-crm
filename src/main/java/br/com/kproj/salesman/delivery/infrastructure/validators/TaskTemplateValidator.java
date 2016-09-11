package br.com.kproj.salesman.delivery.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class TaskTemplateValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;



    @Override
    public boolean supports(Class<?> clazz) {
        return TaskTemplateEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TaskTemplateEntity taskTemplateEntity = (TaskTemplateEntity) target;
        Set<ConstraintViolation<Object>> constraints = validator.validate(taskTemplateEntity);

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
