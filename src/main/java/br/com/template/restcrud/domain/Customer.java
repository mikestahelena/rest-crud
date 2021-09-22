package br.com.template.restcrud.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String document;

    @NotNull
    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company")
    private String company;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public static Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return Customer.builder()
            .id(customerDTO.getId())
            .document(customerDTO.getDocument())
            .customerType(customerDTO.getCustomerType())
            .firstname(customerDTO.getFirstname())
            .lastname(customerDTO.getLastname())
            .birthDate(customerDTO.getBirthDate())
            .country(customerDTO.getCountry())
            .email(customerDTO.getEmail())
            .phoneNumber(customerDTO.getPhoneNumber())
            .jobTitle(customerDTO.getJobTitle())
            .company(customerDTO.getCompany())
            .build();
    }
}
