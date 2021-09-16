package br.com.template.restcrud.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCustomerTypeException extends RuntimeException {

    public InvalidCustomerTypeException(String message) {
        super(message);
    }
}
