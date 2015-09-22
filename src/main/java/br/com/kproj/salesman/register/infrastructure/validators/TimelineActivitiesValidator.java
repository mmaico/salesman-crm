package br.com.kproj.salesman.register.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.register.view.dto.LogActivityVO;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Component
public class TimelineActivitiesValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    private Map<Class<?>, Validator> validatorMap = new HashMap<>();

    {
        validatorMap.put(LogActivity.class, new TimelineLogActivityValidator());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LogActivity.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Validator validator = validatorMap.get(target.getClass());

        if (validator == null) {
            throw new ValidationException(Sets.newHashSet("timeline.invalid.type"));
        }

        validator.validate(target, errors);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch(Exception e) {}
    }

    private class TimelineLogActivityValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return LogActivityVO.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            LogActivity activity = (LogActivity) target;

            Set<ConstraintViolation<Object>> constraints = validator.validate(activity);

            if (isEmpty(activity.getDescription())) {
                errors.rejectValue("logactivity.description", "logactivity.description.is.invalid");
            }

            constraints.forEach(error ->
                    errors.rejectValue(error.getPropertyPath().toString(), error.getMessage()));

        }
    }

}
