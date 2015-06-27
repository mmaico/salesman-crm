package br.com.kproj.salesman.register.infraestructure.validators;

import br.com.kproj.salesman.infrastructure.entity.Company;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import org.springframework.validation.Validator;


import javax.validation.*;
import java.util.Set;

@Configurable
public class CompanyValidator implements Validator, InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Company.class);
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
