package br.com.template.restcrud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import br.com.template.restcrud.domain.Customer;
import br.com.template.restcrud.domain.CustomerDTO;
import br.com.template.restcrud.exception.CustomerNotFoundException;
import br.com.template.restcrud.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

	private static final String CUSTOMER_NOT_FOUND = "CUSTOMER NOT FOUND";

	private final CustomerRepository customerRepository;

	public List<CustomerDTO> getAllCustomers() {
		return getCustomerDTOStream(customerRepository.findAll()).collect(Collectors.toList());
	}

	public CustomerDTO getCustomerById(Long id) {
		return findCustomerById(id).map(CustomerDTO::fromCustomer)
				.orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
	}

	public List<CustomerDTO> getCustomersByName(String name) {
		return getCustomerDTOStream(customerRepository.findByNameContainingIgnoreCase(name))
				.collect(Collectors.toList());
	}

	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		var customer = Customer.fromCustomerDTO(customerDTO);
		customer.setDateCreated(LocalDateTime.now());
		customer.setLastUpdated(LocalDateTime.now());
		customerRepository.save(customer);
		return CustomerDTO.fromCustomer(customer);
	}

	public void deleteCustomer(Long id) {
		var customer = findCustomerById(id).orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
		customerRepository.delete(customer);
	}

	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
		return findCustomerById(id).map(customer -> {
			customer.setName(customerDTO.getName());
			customer.setBirthDate(customerDTO.getBirthDate());
			customer.setEmail(customerDTO.getEmail());
			customer.setPhoneNumber(customerDTO.getPhoneNumber());
			customer.setLastUpdated(LocalDateTime.now());
			return customerRepository.save(customer);
		}).map(CustomerDTO::fromCustomer).orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
	}

	private Optional<Customer> findCustomerById(Long id) {
		return Optional.of(customerRepository.findById(id)).get();
	}

	private Stream<CustomerDTO> getCustomerDTOStream(Iterable<Customer> customerIterable) {
		return StreamSupport.stream(customerIterable.spliterator(), false).map(CustomerDTO::fromCustomer);
	}

}
