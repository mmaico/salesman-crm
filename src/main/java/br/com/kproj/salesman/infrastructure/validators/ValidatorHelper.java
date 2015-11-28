package br.com.kproj.salesman.infrastructure.validators;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

public class ValidatorHelper {


    public static Boolean hasContraintViolated(Object target, Validator validator) {
        BindException bindException = new BindException(target, target.getClass().getSimpleName());
        validator.validate(target, bindException);

        if (!bindException.getAllErrors().isEmpty()) {
            throw new ValidationException(bindException.getAllErrors());
        }

        return Boolean.FALSE;
    }
}
