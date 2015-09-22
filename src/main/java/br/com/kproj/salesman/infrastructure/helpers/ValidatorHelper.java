package br.com.kproj.salesman.infrastructure.helpers;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorHelper {

        private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorHelper.class);
        private static final Validator javaxValidator = Validation.buildDefaultValidatorFactory().getValidator();
        private static final SpringValidatorAdapter validator = new SpringValidatorAdapter(javaxValidator);

        public static Object validate(Object entry) {
            Errors errors = new BeanPropertyBindingResult(entry, entry.getClass().getName());
            validator.validate(entry, errors);
            if (errors == null || errors.getAllErrors().isEmpty())
                return entry;
            else {
                LOGGER.error(errors.toString());
                throw new ValidationException(errors.getAllErrors());
            }
        }

}
