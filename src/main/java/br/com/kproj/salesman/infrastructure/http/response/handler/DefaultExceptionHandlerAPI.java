package br.com.kproj.salesman.infrastructure.http.response.handler;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ErrorHandlerResult;
import br.com.kproj.salesman.infrastructure.logging.LogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultExceptionHandlerAPI {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandlerAPI.class);
  private LogBuilder logBuilder;
  
  @Autowired
  public DefaultExceptionHandlerAPI(final LogBuilder logBuilder) {
    this.logBuilder = logBuilder;
  }

  @ExceptionHandler({RuntimeException.class, Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorHandlerResult repositoryError(final Exception e) {
    LOGGER.error("", e);
    return buildErrorHandlerResult(e, HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
  
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorHandlerResult resourceNotFound(final NotFoundException e) {
    return buildErrorHandlerResult(e, HttpStatus.NOT_FOUND.value());
  }
  
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorHandlerResult pathNotFound(final NoHandlerFoundException e) {
    return buildErrorHandlerResult(e, HttpStatus.NOT_FOUND.value());
  }
  
  @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  @ResponseBody
  public ErrorHandlerResult methodNotAllowed(final HttpRequestMethodNotSupportedException e) {
    return buildErrorHandlerResult(e, HttpStatus.METHOD_NOT_ALLOWED.value());
  }
  
  @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
  @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ResponseBody
  public ErrorHandlerResult unsupportedMediaType(final HttpMediaTypeNotSupportedException e) {
    return buildErrorHandlerResult(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
  }
  
  @ExceptionHandler( MethodArgumentNotValidException.class)
  @ResponseBody
  @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
  public ErrorHandlerResult handleDMSRESTException(MethodArgumentNotValidException e) {
    return buildErrorHandlerResult(e, HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseBody
  @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
  public ErrorHandlerResult handleValidationException(ValidationException exception) {
    LOGGER.error(logBuilder.buildFrom(exception.getErrors(), "handleValidationException", Boolean.FALSE));
    return new ErrorHandlerResult().addErrors(exception.getErrors().toString(), HttpStatus.BAD_REQUEST.value());
  }
    
  private ErrorHandlerResult buildErrorHandlerResult(final Exception exception, final int code) {
    LOGGER.error(logBuilder.buildFrom(exception, "buildErrorHandlerResult"));
    return new ErrorHandlerResult().addErrors(exception.getMessage(), code);
  }
}
