package br.com.kproj.salesman.register.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.person.Company;
import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Map;


@Component
public class ClientValidator implements Validator {

    private static final String COMPANY = "company";
    private static final String INDIVIDUAL = "individual";


    private Map<String, Validator> validators = Maps.newHashMap();

    @Autowired
    public ClientValidator(CompanyValidator companyValidator, IndividualValidator individualValidator) {
        validators.put(COMPANY, companyValidator);
        validators.put(INDIVIDUAL, individualValidator);
    }


    @Override
    public boolean supports(Class<?> paramClass) {
        return Boolean.TRUE;
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (target instanceof Company) {
            Validator validator = validators.get(COMPANY);
            ValidationUtils.invokeValidator(validator, target, errors);
        } else if (target instanceof Individual) {
            Validator validator = validators.get(INDIVIDUAL);
            ValidationUtils.invokeValidator(validator, target, errors);
        } else {
            throw new ValidationException(Sets.newHashSet("client.not.found.validator"));
        }

    }
}
