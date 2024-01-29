package com.task.manager.modules.spring.exception;

import com.task.manager.modules.validation.Error;
import com.task.manager.modules.validation.Exceptions.DomainException;
import com.task.manager.modules.validation.Exceptions.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(
            final DomainException ex
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }



    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handlerDomainException(
            final DomainException ex
    ){
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }



    record ApiError(String message, List<Error> errors){
        static ApiError from (final DomainException ex){
            return new ApiError(ex.getMessage(), ex.getErrors());
        }
    }
}
