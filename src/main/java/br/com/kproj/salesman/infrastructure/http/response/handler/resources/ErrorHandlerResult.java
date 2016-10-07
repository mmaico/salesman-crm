package br.com.kproj.salesman.infrastructure.http.response.handler.resources;


import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ErrorResource;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@ErrorResource
public class ErrorHandlerResult {
  
  public class Error {
    
    private final String message;
    private final int code;
    
    private Error(final String message, final int code) {
      this.message = message;
      this.code = code;
    }

    public String getMessage() {
      return message;
    }

    public int getCode() {
      return code;
    }
    
    @Override
    public boolean equals(final Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      final Error that = (Error) o;
      return Objects.equals(getMessage(), that.getMessage()) &&
          Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getMessage(), getCode());
    }
 
  }

  public class Errors {
    private final List<Error> messages = new LinkedList<>();
    
    private Errors() {}
    
    public List<Error> getMessages() {
      return messages;
    }
    
    @Override
    public boolean equals(final Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      final Errors that = (Errors) o;
      return Objects.equals(getMessages(), that.getMessages());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getMessages(), getMessages());
    }
  }

  private String uri;
  private final Errors errors;
  
  public ErrorHandlerResult(){
    this.errors = new Errors();
  }
  
  public ErrorHandlerResult addErrors(final String msg, final int code) {
    this.errors.messages.add(new Error(msg, code)); 
    return this;
  }
  
  public Errors getErrors() {
    return errors;
  }

  public void setUri(final String uri) {
    this.uri = uri;
  }
  
  public String getUri() {
    return this.uri; 
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final ErrorHandlerResult that = (ErrorHandlerResult) o;
    return Objects.equals(getErrors(), that.getErrors()) &&
        Objects.equals(getUri(), that.getUri());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getErrors(), getUri());
  }
}
