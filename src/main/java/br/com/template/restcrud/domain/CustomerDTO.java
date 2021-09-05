package br.com.template.restcrud.domain;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

	private Long id;

	@NotNull
	private String cpf;

	@NotNull
	private String name;

	@NotNull
	private LocalDate birthDate;

	@Email
	private String email;

	private String phoneNumber;

	public static CustomerDTO fromCustomer(Customer customer) {
		return CustomerDTO.builder().id(customer.getId()).cpf(customer.getCpf()).name(customer.getName())
				.birthDate(customer.getBirthDate()).email(customer.getEmail()).phoneNumber(customer.getPhoneNumber())
				.build();
	}

}
