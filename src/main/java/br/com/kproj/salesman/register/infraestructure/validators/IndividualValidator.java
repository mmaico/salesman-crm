package br.com.kproj.salesman.register.infraestructure.validators;

import br.com.kproj.salesman.infrastructure.entity.Company;
import br.com.kproj.salesman.infrastructure.entity.Individual;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Configurable
public class IndividualValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Individual.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validator.validate(target).forEach(error ->
                errors.rejectValue("error", error.getMessage()) );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
}
