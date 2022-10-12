package br.com.matheus.mypatrimony.error;

import br.com.matheus.mypatrimony.message.ApiMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        final List<ApiMessage.FieldError> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(field ->{
            FieldError f = (FieldError) field;
            fields.add(new ApiMessage.FieldError( f.getField() , f.getDefaultMessage()));
        });

        return handleExceptionInternal(ex,
                new ApiMessage(HttpStatus.BAD_REQUEST, "Falha na validação dos campos", fields),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){

        return handleExceptionInternal(ex,
                new ApiMessage(HttpStatus.BAD_REQUEST, "Json inválido", new ArrayList<>()),
                headers,
                HttpStatus.BAD_REQUEST,
                request);

    }

}