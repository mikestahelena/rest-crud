package br.com.template.restcrud.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.template.restcrud.domain.Customer;
import br.com.template.restcrud.domain.CustomerDTO;
import br.com.template.restcrud.domain.CustomerType;
import br.com.template.restcrud.exception.CustomerNotFoundException;
import br.com.template.restcrud.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

    private static final String CUSTOMER_NOT_FOUND = "CUSTOMER NOT FOUND";

    private final CustomerRepository customerRepository;

    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        final var page = customerRepository.findAll(pageable);
        return new PageImpl<>(page.getContent()
            .stream()
            .map(CustomerDTO::fromCustomer)
            .collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    public CustomerDTO getCustomerById(Long id) {
        return findCustomerById(id).map(CustomerDTO::fromCustomer)
            .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
    }

    public CustomerDTO getCustomerByDocument(String document) {
        return customerRepository.findByDocument(document)
            .map(CustomerDTO::fromCustomer)
            .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
    }

    public Page<CustomerDTO> getCustomersByName(String name, Pageable pageable) {
        final var page = customerRepository.findByNameContainingIgnoreCase(name, pageable);
        return new PageImpl<>(page.getContent()
            .stream()
            .map(CustomerDTO::fromCustomer)
            .collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    public Page<CustomerDTO> getCustomerByCustomerType(CustomerType customerType, Pageable pageable) {
        final var page = customerRepository.findByCustomerType(customerType, pageable);
        return new PageImpl<>(page.getContent()
            .stream()
            .map(CustomerDTO::fromCustomer)
            .collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        var customer = Customer.fromCustomerDTO(customerDTO);
        customer.setDateCreated(LocalDateTime.now());
        customer.setLastUpdated(LocalDateTime.now());
        customerRepository.save(customer);
        return CustomerDTO.fromCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        return findCustomerById(id).map(customer -> {
            customer.setName(customerDTO.getName());
            customer.setBirthDate(customerDTO.getBirthDate());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
            customer.setLastUpdated(LocalDateTime.now());
            return customerRepository.save(customer);
        })
            .map(CustomerDTO::fromCustomer)
            .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
    }

    private Optional<Customer> findCustomerById(Long id) {
        return Optional.of(customerRepository.findById(id))
            .get();
    }
}
