package com.nekochan.redchan.exception;

import com.nekochan.redchan.payload.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralizedException {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse<String>> handleDataNotFound(DataNotFoundException exception) {
        ExceptionResponse<String> exceptionResponse = new ExceptionResponse<>(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}
