package br.com.tiacademy.exameagenda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.tiacademy.exameagenda.dto.DefaultError;

@ControllerAdvice
public class AplicationExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<DefaultError> handleException(Exception e){

        DefaultError erro = new DefaultError(HttpStatus.BAD_GATEWAY.value(), "Erro de gatway");
        return new ResponseEntity<DefaultError>(erro, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<DefaultError> handleExceptionA(Exception e){

        DefaultError erro = new DefaultError(HttpStatus.BAD_GATEWAY.value(), "Não encontrado");
        return new ResponseEntity<DefaultError>(erro, HttpStatus.BAD_GATEWAY);
    }
}
