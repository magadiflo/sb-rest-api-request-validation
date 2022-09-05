package com.magadiflo.app.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //Detalles del error
        InvalidFormatException invalid = (InvalidFormatException) ex.getCause();
        String inputValue = invalid.getValue().toString();
        String errorMessage = invalid.getOriginalMessage();
        String field = invalid.getPath().stream().limit(1).map(JsonMappingException.Reference::getFieldName).findAny().get();
        ErrorDetalleAdicional errorDetalleAdicional = new ErrorDetalleAdicional(field, inputValue, errorMessage);

        Map<String, Object> errorDetalle = new HashMap<>();
        errorDetalle.put("not-readable", "Solicitud no completada. Se detectó solicitud JSON con formato incorrecto.");
        errorDetalle.put("detalles", errorDetalleAdicional);

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("errors", Arrays.asList(errorDetalle));

        return new ResponseEntity<>(responseBody, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errorGeneral = new HashMap<>();
        errorGeneral.put("global", "Ocurrió un error al procesar la solicitud.");

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("errors", Arrays.asList(errorGeneral));

        return new ResponseEntity<>(responseBody, headers, HttpStatus.BAD_REQUEST);
    }

    class ErrorDetalleAdicional {
        public final String campo;
        public final String valorIngresado;
        public final String mensajeError;

        public ErrorDetalleAdicional(String campo, String valorIngresado, String mensajeError) {
            this.campo = campo;
            this.valorIngresado = valorIngresado;
            this.mensajeError = mensajeError;
        }
    }

}

