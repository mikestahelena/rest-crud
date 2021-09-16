package br.com.template.restcrud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.template.restcrud.domain.Customer;
import br.com.template.restcrud.domain.CustomerType;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    Iterable<Customer> findByNameContainingIgnoreCase(String name);

    Iterable<Customer> findByCustomerType(CustomerType customerType);

}
