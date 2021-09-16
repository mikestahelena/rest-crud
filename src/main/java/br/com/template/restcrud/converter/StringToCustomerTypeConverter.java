package br.com.template.restcrud.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.template.restcrud.domain.CustomerType;

@Component
public class StringToCustomerTypeConverter implements Converter<String, CustomerType> {

    @Override
    public CustomerType convert(String source) {
        return CustomerType.from(source);
    }
}
