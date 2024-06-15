package com.etraveli.movierental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{

    //Handling validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now()));
    }

    //Handling custom Rental exceptions
    @ExceptionHandler(RentalException.class)
    public ResponseEntity<ErrorResponse> handleRentalException(RentalException rentalException) {
        return buildErrorResponse(rentalException.getError());
    }

    //Handling all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now()));
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(RentalError rentalError) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(rentalError.getCode(), rentalError.getMessage(), rentalError.getTimestamp()));
    }

    public record ErrorResponse(int code, String message, LocalDateTime timestamp) {
    }

}
