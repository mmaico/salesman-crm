package br.com.kproj.salesman.infrastructure.logging;

import java.util.Map;
import java.util.Set;

/**
 * Constroi strings para utilizar em logs.
 */
public interface LogBuilder {

  /**
   * Constroi a string a partir de uma exception.
   *
   * @param throwable a throwable
   * @param operation a operação que era realizada no momento da ocorrência da exception
   * @return a string criada
   */
  String buildFrom(final Throwable throwable, final String operation);


  /**
   * Constroi a string a partir de uma mensagem e operação.
   *
   * @param message a mensagem
   * @param operation a operação que era realizada no momento da ocorrência da exception
   * @return a string criada
   */
  String buildSuccessMessage(String message, final String operation);

  /**
   * Constroi a string a partir de um mapa de informacoes.
   *
   * @param attributes Atributos da informacao que vai ser gerada
   * @param operation A operação que era realizada no momento da ocorrência
   * @param result Resultado da operacao, se foi positivo ou negativo 
   * @return String criada
   */
  String buildFrom(final Map<String, Object> attributes, final String operation, final Boolean result);
  
  /**
   * Constroi a string a partir de um lista de erros.
   *
   * @param errors Atributos da informacao que vai ser gerada
   * @param operation A operação que era realizada no momento da ocorrência
   * @param result Resultado da operacao, se foi positivo ou negativo 
   * @return String criada
   */
  String buildFrom(final Set<String> errors, final String operation, final Boolean result);

}
