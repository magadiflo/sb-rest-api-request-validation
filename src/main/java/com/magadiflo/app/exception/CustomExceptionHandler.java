package com.magadiflo.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ControllerAdvice, interceptará los errores producidos en los controladores (resources);
 * en este caso en particular, como sobreescribimos el método handleMethodArgumentNotValid(...),
 * cada vez que ocurra una excepción del argumento del método en los controladores (argumentos
 * no válidos) es que se mandará a llamar a este método sobreescrito.
 * Aquí se puede personalizar un mensaje de respuesta.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        //************* FORMA 01:  Muestra solo los errores
        //List<String> errorsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());



        //************* FORMA 02: Muestra el nombre del campo donde se produjo el error agrupando todos sus errores producidos
        //Ejemplo de la agrupación tomado de la siguiente web
        //https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
        /*
        Map<String, List<String>> errorsMessage = fieldErrors.stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(fieldError -> fieldError.getDefaultMessage(), Collectors.toList())
                        )
                );
         */


        //************* FORMA 03: Muestra el nombre del campo y el error producido
        List<Map<String, String>> errorsMessage = fieldErrors.stream().map(fieldError -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return errorMap;
        }).collect(Collectors.toList());

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("errors", errorsMessage);
        return new ResponseEntity<>(responseBody, headers, HttpStatus.BAD_REQUEST);
    }
}
