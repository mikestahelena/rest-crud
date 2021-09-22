package br.com.template.restcrud.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

    private Long id;

    @NotNull
    private String document;

    @NotNull
    private CustomerType customerType;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    private LocalDate birthDate;

    private String country;

    @NotNull
    private String email;

    private String phoneNumber;

    private String jobTitle;

    private String company;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

    public static CustomerDTO fromCustomer(Customer customer) {
        return CustomerDTO.builder()
            .id(customer.getId())
            .document(customer.getDocument())
            .customerType(customer.getCustomerType())
            .firstname(customer.getFirstname())
            .lastname(customer.getLastname())
            .birthDate(customer.getBirthDate())
            .country(customer.getCountry())
            .email(customer.getEmail())
            .phoneNumber(customer.getPhoneNumber())
            .jobTitle(customer.getJobTitle())
            .company(customer.getCompany())
            .dateCreated(customer.getDateCreated())
            .lastUpdated(customer.getLastUpdated())
            .build();
    }

}
