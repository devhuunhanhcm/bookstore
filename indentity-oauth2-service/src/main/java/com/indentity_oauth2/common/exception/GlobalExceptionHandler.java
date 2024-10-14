package com.indentity_oauth2.common.exception;

import com.indentity_oauth2.common.helper.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    Object handlingRuntimeException(RuntimeException e){
        return ResponseHelper.getErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
