package br.com.template.restcrud.controller;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.template.restcrud.converter.StringToCustomerTypeConverter;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final StringToCustomerTypeConverter stringToCustomerTypeConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToCustomerTypeConverter);
    }
}
