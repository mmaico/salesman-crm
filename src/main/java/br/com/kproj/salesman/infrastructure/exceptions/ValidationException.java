package br.com.kproj.salesman.infrastructure.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;


public class ValidationException extends RuntimeException {

    private List<ObjectError> errors;

    public ValidationException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
