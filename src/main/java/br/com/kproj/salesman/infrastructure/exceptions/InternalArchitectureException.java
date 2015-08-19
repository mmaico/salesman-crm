package br.com.kproj.salesman.infrastructure.exceptions;


public class InternalArchitectureException extends RuntimeException {

    public InternalArchitectureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalArchitectureException(final String message) {
        super(message);
    }

    public InternalArchitectureException(Exception e) {
        super(e);
    }

}
