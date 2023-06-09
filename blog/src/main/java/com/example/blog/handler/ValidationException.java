package com.example.blog.handler;

import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RestController
@Slf4j
public class ValidationException {

    private final Logger LOGGER = LoggerFactory.getLogger(ValidationException.class);

    //안됨 추후 확인 필요
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Object exception(Exception e) {
        LOGGER.info(e.getMessage());
        return e.getMessage();
    }

}
