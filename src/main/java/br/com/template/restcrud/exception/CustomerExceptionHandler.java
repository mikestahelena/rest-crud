package br.com.template.restcrud.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorMessage> customerNotFoundExceptionHandler(CustomerNotFoundException ex,
			WebRequest request) {
		return new ResponseEntity<>(new ErrorMessage(NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false)), NOT_FOUND);
	}
}
