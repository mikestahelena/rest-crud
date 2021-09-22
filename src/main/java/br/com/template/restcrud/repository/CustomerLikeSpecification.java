package br.com.template.restcrud.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.AllArgsConstructor;

import org.springframework.data.jpa.domain.Specification;

import br.com.template.restcrud.domain.Customer;

@AllArgsConstructor
public class CustomerLikeSpecification implements Specification<Customer> {

    private String field;
    private String fieldName;

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (field == null) {
            return cb.isTrue(cb.literal(true));
        }
        return cb.like(cb.lower(root.get(fieldName)
            .as(String.class)), '%' + field.toLowerCase() + '%');
    }
}
