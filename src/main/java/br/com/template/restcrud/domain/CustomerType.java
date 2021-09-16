package br.com.template.restcrud.domain;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import br.com.template.restcrud.exception.InvalidCustomerTypeException;

@AllArgsConstructor
@Getter
@ToString
public enum CustomerType {
    NATURAL,
    LEGAL;

    public static CustomerType from(String type) {
        return Stream.of(CustomerType.values())
            .filter(b -> b.name()
                .equalsIgnoreCase(type))
            .findAny()
            .orElseThrow(() -> new InvalidCustomerTypeException("Invalid CustomerType"));
    }
}
