package br.com.template.restcrud.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String cpf;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public static Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return Customer.builder()
                .cpf(customerDTO.getCpf())
                .name(customerDTO.getName())
                .birthDate(customerDTO.getBirthDate())
                .email(customerDTO.getEmail())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();
    }
}
