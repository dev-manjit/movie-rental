package com.etraveli.movierental.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({RentalException.class})
    public ResponseEntity<ErrorResponse> handleDivideUpException(RentalException rentalException) {
        return buildErrorResponse(rentalException.getError());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(RentalError rentalError) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(rentalError.getCode(), rentalError.getMessage(), rentalError.getTimestamp()));
    }
    public record ErrorResponse(int code, String message, LocalDateTime timestamp) {
    }

}
