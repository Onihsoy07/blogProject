package com.example.blog.handler;

import com.example.blog.dto.ResponseDto;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalIdentifierException.class)
    public ResponseDto<String> hadleIllegalIdentifierException(IllegalIdentifierException e) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> hadleException(Exception e) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }



}
