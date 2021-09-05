package br.com.template.restcrud.repository;

import br.com.template.restcrud.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    Iterable<Customer> findByNameContainingIgnoreCase(String name);

}
