package br.com.kproj.salesman.infrastructure.exceptions;

public class NotFoundException extends BaseException{

  private static String DEFAULT_MESSAGE = "Resource not found.";
  
  public NotFoundException(String message, Object[] params) {
    super(message, params);
  }

  public NotFoundException(String message) {
    super(message);
  }
  
  public NotFoundException() {
    super(DEFAULT_MESSAGE);
  }


}
