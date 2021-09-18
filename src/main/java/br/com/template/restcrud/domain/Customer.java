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

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public static Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return Customer.builder()
            .document(customerDTO.getDocument())
            .customerType(customerDTO.getCustomerType())
            .name(customerDTO.getName())
            .birthDate(customerDTO.getBirthDate())
            .email(customerDTO.getEmail())
            .phoneNumber(customerDTO.getPhoneNumber())
            .build();
    }
}
