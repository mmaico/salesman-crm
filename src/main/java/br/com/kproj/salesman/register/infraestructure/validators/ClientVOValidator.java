package br.com.kproj.salesman.register.infraestructure.validators;

import br.com.kproj.salesman.register.view.dto.ClientDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by marcelomaicodejesus on 6/27/15.
 */
@Component
public class ClientVOValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return ClientDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        System.out.println("teste");
    }
}
