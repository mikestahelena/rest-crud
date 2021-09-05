package br.com.template.restcrud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.template.restcrud.domain.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	Iterable<Customer> findByNameContainingIgnoreCase(String name);

}
