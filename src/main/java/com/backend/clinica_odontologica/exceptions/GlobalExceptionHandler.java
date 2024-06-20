package com.backend.clinica_odontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado: " + resourceNotFoundException.getMessage());
        return mensaje;
    }

    //Requerimiento manejo de BadRequestException
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> mensaje = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(e -> {
            String nombreCampo = ((FieldError) e).getField();
            String mensajeError = e.getDefaultMessage();
            mensaje.put(nombreCampo, mensajeError);
        });
        return mensaje;
    }
    //HttpMessageNotReadableException
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "El formato del mensaje no es legible o es incorrecto: " + ex.getMessage());
        return mensaje;
    }

    // Manejo de BadRequestException
    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarBadRequestException(BadRequestException badRequestException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", badRequestException.getMessage());
        return mensaje;
    }

}
