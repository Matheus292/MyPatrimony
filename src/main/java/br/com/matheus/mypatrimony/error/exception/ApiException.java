package br.com.matheus.mypatrimony.error.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiException{
    private HttpStatus status;
    private String message;
    private List<FieldError> errors = new ArrayList<>();

    public ApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @AllArgsConstructor
    @Data
    public static class FieldError{
        private String field;
        private String message;
    }
}
