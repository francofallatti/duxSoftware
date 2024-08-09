package com.dux.software.controller;

import com.dux.software.dto.ErrorDto;
import com.dux.software.exceptions.EquipoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EquipoNoEncontradoException.class)
    public ResponseEntity<ErrorDto> handleEquipoNoEncontradoException(EquipoNoEncontradoException ex) {
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .codigo(HttpStatus.NOT_FOUND.value())
                        .mensaje(ex.getMessage())
                        .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .codigo(HttpStatus.BAD_REQUEST.value())
                        .mensaje("La solicitud es invalida")
                        .build()
                , HttpStatus.BAD_REQUEST);
    }
}
