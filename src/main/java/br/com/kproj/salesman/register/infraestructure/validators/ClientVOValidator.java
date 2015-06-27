package br.com.kproj.salesman.register.infraestructure.validators;

import br.com.kproj.salesman.infrastructure.helpers.ReflectionsUtils;
import br.com.kproj.salesman.register.view.dto.ClientDTO;

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
    Map<String, Validator> validators = new HashMap<>();

    {
        validators.put(COMPANY, new CompanyValidator());
        validators.put(INDIVIDUAL, new IndividualValidator());
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return ClientDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ClientDTO clientDTO = (ClientDTO) target;
        Validator validator = validators.get(clientDTO.getType());
        validator.validate(target, errors);

    }
}
