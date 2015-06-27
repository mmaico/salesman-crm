package br.com.kproj.salesman.register.infraestructure.validators;

import br.com.kproj.salesman.infrastructure.helpers.ReflectionsUtils;
import br.com.kproj.salesman.register.view.dto.ClientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;


@Component
public class ClientVOValidator implements Validator {

    private static final String COMPANY = "company";
    private static final String INDIVIDUAL = "individual";


    private Map<String, Validator> validators = new HashMap<>();

    @Autowired
    public ClientVOValidator(CompanyValidator companyValidator, IndividualValidator individualValidator) {
        validators.put(COMPANY, companyValidator);
        validators.put(INDIVIDUAL, individualValidator);
    }


    @Override
    public boolean supports(Class<?> paramClass) {
        return ClientDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ClientDTO clientDTO = (ClientDTO) target;
        Validator validator = validators.get(clientDTO.getType());

        ValidationUtils.invokeValidator(validator, clientDTO.getClient(), errors);

    }
}
