package com.hh.TaskApp.exception;

import com.hh.TaskApp.dto.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> response(IllegalArgumentException e) {
        LOGGER.warn("Argument invalid exception: ", e);
        return new ResponseEntity<>(new ApiError("неверный параметр запроса",
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceForbiddenException.class)
    public ResponseEntity<ApiError> response(ResourceForbiddenException e) {
        LOGGER.warn("access denied : ", e);
        return new ResponseEntity<>(new ApiError("доступ запрещен",
                HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ApiError> response(InternalErrorException e) {
        LOGGER.warn("internal error : ", e);
        return new ResponseEntity<>(new ApiError("сервис временно недоступен",
                HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
