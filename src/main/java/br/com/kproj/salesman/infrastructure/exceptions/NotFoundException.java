package br.com.kproj.salesman.infrastructure.exceptions;

public class NotFoundException extends BaseException{

  private static String DEFAULT_MESSAGE = "Recurso não encontrado."; 
  
  public NotFoundException(String message, Object[] params) {
    super(message, params);
  }
  
  public NotFoundException() {
    super(DEFAULT_MESSAGE);
  }


}
