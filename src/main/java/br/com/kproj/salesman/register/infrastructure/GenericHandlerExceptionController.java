package br.com.kproj.salesman.register.infrastructure;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ControllerAdvice
public class GenericHandlerExceptionController {

    private Log log = LogFactory.getLog(GenericHandlerExceptionController.class);

    @ExceptionHandler(value={ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handlerValidatorException(Throwable throwable) {

        return new ModelAndView("/assets/includes/vm/errors", "errors", ((ValidationException)throwable).getErrors());
    }


    @ExceptionHandler(value={BadCredentialsException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody ResponseEntity unauthorized(Throwable throwable) {

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String handlerInternalException(Throwable throwable) {
        log.error(ExceptionUtils.getMessage(throwable));
        return ExceptionUtils.getFullStackTrace(throwable);
    }
}
