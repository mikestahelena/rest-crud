package br.com.template.restcrud.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.template.restcrud.domain.Customer;
import br.com.template.restcrud.domain.CustomerType;

public interface CustomerRepository
        extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByDocument(String document);

    Page<Customer> findByCustomerType(CustomerType customerType, Pageable pageable);

}
