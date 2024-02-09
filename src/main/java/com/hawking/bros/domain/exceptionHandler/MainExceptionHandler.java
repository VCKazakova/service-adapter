package com.hawking.bros.domain.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AdapterException> handleValidationException(MethodArgumentNotValidException ex) {
        AdapterException adapterException =
                new AdapterException("Валидация не пройдена" + ex.getMessage());
        return new ResponseEntity<>(adapterException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(feign.FeignException.class)
    public ResponseEntity<AdapterException> handleAnotherFeignException(feign.FeignException ex) {
        AdapterException adapterException =
                new AdapterException("Во время обращения к GisMeteo произошла ошибка" + ex.getMessage());
        return new ResponseEntity<>(adapterException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterException> handleMappingException(Exception ex) {
        AdapterException adapterException =
                new AdapterException("Во время работы приложения произошла ошибка" + ex.getMessage());
        return new ResponseEntity<>(adapterException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
