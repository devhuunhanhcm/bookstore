package com.profile_service.common.exception;

import com.profile_service.common.helper.ResponseHelper;
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
