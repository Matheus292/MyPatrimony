package br.com.matheus.mypatrimony.error;

import br.com.matheus.mypatrimony.error.exception.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({ ApiException.class })
    public ResponseEntity<ApiException> handleExceptionApi(
            ApiException ex) {
        return new ResponseEntity<ApiException>(
                ex, new HttpHeaders(), ex.getStatus());
    }

}