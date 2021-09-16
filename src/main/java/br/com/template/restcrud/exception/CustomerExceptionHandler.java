package br.com.template.restcrud.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorMessage> customerNotFoundExceptionHandler(CustomerNotFoundException ex,
            WebRequest request) {
        return new ResponseEntity<>(getErrorMessage(request, NOT_FOUND, ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(InvalidCustomerTypeException.class)
    public ResponseEntity<ErrorMessage> invalidCustomerTypeExceptionHandler(InvalidCustomerTypeException ex,
            WebRequest request) {
        return new ResponseEntity<>(getErrorMessage(request, BAD_REQUEST, ex.getMessage()), BAD_REQUEST);
    }

    private ErrorMessage getErrorMessage(WebRequest request, HttpStatus httpStatus, String message) {
        return new ErrorMessage(httpStatus.value(), LocalDateTime.now(), message, request.getDescription(false));
    }

}
