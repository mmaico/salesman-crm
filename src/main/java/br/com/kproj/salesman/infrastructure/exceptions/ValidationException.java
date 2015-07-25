package br.com.kproj.salesman.infrastructure.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ValidationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<String> errors;

    public ValidationException(List<ObjectError> errors) {
        this.errors = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toSet());
    }

    public ValidationException(Set<String> errors) {
        this.errors = errors;
    }

    public Set<String> getErrors() {
        return errors;
    }
}
