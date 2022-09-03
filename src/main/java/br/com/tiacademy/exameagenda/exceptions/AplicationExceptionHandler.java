package br.com.tiacademy.exameagenda.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AplicationExceptionHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler(IndexOutOfBoundsException.class)
    // public ResponseEntity<Object> handleException(Exception e) {

    //     DefaultError erro = new DefaultError(HttpStatus.BAD_GATEWAY.value(), "Erro de gateway");

    //     return new ResponseEntity<>(erro, HttpStatus.BAD_GATEWAY);
    // }

    // @ExceptionHandler(NullPointerException.class)
    // public ResponseEntity<DefaultError> handleExceptionA(Exception e) {

    // DefaultError erro = new DefaultError(HttpStatus.BAD_GATEWAY.value(), "Não
    // encontrado");
    // return new ResponseEntity<DefaultError>(erro, HttpStatus.BAD_GATEWAY);
    // }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest wr) {

        String errorDescription = e.getLocalizedMessage();

        if (errorDescription == null) {
            errorDescription = e.toString();
        }

        var code = HttpStatus.NOT_FOUND.value();

        var message = new MessageResponse(new Date(), errorDescription, code);

        return new ResponseEntity<>(message, new HttpHeaders(), code);
    }
}
