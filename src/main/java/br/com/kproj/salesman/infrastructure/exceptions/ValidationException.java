package br.com.kproj.salesman.infrastructure.exceptions;

import com.google.common.collect.Sets;
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
        this.errors = errors.stream().map(ObjectError::getCode).collect(Collectors.toSet());
    }

    public ValidationException(Set<String> errors) {
        this.errors = errors;
    }

    public ValidationException(String error) {
        this.errors = Sets.newHashSet(error);
    }

    public Set<String> getErrors() {
        return errors;
    }

    public static ValidationException createThrow(String message) {
        return new ValidationException(message);
    }
}
