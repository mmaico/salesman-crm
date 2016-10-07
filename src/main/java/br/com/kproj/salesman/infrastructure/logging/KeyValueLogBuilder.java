package br.com.kproj.salesman.infrastructure.logging;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Constroi strings no formato chave-valor.
 */
@Component
public class KeyValueLogBuilder implements LogBuilder {

  @Override
  public String buildFrom(Throwable throwable, String operation) {
    return String.format("{operation: '%s',result: 'NOK',exception: '%s',reason: '%s'}", operation, throwable.getClass()
        .getSimpleName(), throwable.getMessage());
  }

  @Override
  public String buildSuccessMessage(final String message, final String operation) {
    return String.format("{operation: '%s',result: 'OK',message: '%s'}", operation, message);
  }

  @Override
  public String buildFrom(final Map<String, Object> attributes, final String operation, final Boolean result) {

    final StringBuilder errorAttributesStringBuilder = new StringBuilder();
    final String resultString = result ? "OK" : "NOK";

    if (attributes != null && !attributes.isEmpty()) {

      final Map.Entry<String, Object> firstError = attributes.entrySet().stream().findFirst().get();
      errorAttributesStringBuilder.append(firstError.getKey())
          .append(": ")
          .append("'").append(firstError.getValue().toString()).append("'");

      attributes.entrySet().stream().skip(1).forEach(
          error -> errorAttributesStringBuilder
              .append(",")
              .append(error.getKey()).append(": ")
              .append("'")
              .append(error.getValue().toString())
              .append("'")
      );
    }
    return String.format("{operation: '%s',result: '" + resultString + "',info: {%s}", operation,
        errorAttributesStringBuilder.toString() + "}");
  }

  @Override
  public String buildFrom(Set<String> errors, String operation, Boolean result) {
    final StringBuilder errorAttributesStringBuilder = new StringBuilder();
    final String resultString = result ? "OK" : "NOK";

    if (errors != null && !errors.isEmpty()) {
      
      final String firstError = errors.stream().findFirst().get();
      
      errorAttributesStringBuilder.append(firstError);
      
      errors.stream().skip(1).forEach(
          error -> errorAttributesStringBuilder
              .append(", ")
              .append(error)
      );
    }
    return String.format("{operation: '%s',result: '" + resultString + "',info: {%s}", operation,
        errorAttributesStringBuilder.toString() + "}");
  }

}
