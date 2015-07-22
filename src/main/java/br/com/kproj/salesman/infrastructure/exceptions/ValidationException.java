package br.com.kproj.salesman.infrastructure.exceptions;

import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ValidationException extends RuntimeException {

    private Set<String> errors;

    public ValidationException(List<FieldError> errors) {
        this.errors = errors.stream().map(FieldError::getField).collect(Collectors.toSet());
    }

    public ValidationException(Set<String> errors) {
        this.errors = errors;
    }

    public Set<String> getErrors() {
        return errors;
    }
}
