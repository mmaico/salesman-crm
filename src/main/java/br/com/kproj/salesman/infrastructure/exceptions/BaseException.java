package br.com.kproj.salesman.infrastructure.exceptions;

public abstract class BaseException extends RuntimeException {

  private static final long serialVersionUID = 7844817888126116028L;

  public BaseException() {
    super();
  }

  public BaseException(final String message) {
    super(message);
  }

  public BaseException(final Throwable cause) {
    super(cause);
  }

  public BaseException(final String message, Throwable cause) {
    super(message, cause);
  }

  public BaseException(final String format, final Object... params) {
    super(String.format(format, params));
  }

}
