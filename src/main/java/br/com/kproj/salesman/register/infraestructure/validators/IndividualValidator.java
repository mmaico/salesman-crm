package br.com.kproj.salesman.register.infraestructure.validators;

import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.text.MessageFormat;

@Component
public class IndividualValidator implements Validator, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndividualValidator.class);

    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Individual.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validator.validate(target).forEach(error ->
                errors.rejectValue(MessageFormat.format("individual.{0}", error.getPropertyPath().toString()), error.getMessage()));
    }

    @Override
    public void afterPropertiesSet()  {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            this.validator = factory.getValidator();
        } catch (Exception e) {
            LOGGER.warn("Something got wrong when getting validator");
        }

    }
}
