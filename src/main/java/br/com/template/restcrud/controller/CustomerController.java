package br.com.template.restcrud.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.template.restcrud.domain.CustomerDTO;
import br.com.template.restcrud.domain.CustomerType;
import br.com.template.restcrud.service.CustomerService;

@RestController
@RequestMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CustomerController {

    public static final int DEFAULT_REQUEST_SIZE = 50;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> getCustomersByAttribute(@RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname, @RequestParam(required = false) String country,
            @PageableDefault(sort = "firstname", direction = Sort.Direction.ASC, size = DEFAULT_REQUEST_SIZE) Pageable pageable) {
        return new ResponseEntity<>(customerService.getCustomersByAttribute(firstname, lastname, country, pageable),
                OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), OK);
    }

    @GetMapping("/find/{document}")
    public ResponseEntity<CustomerDTO> getCustomerByDocument(@PathVariable("document") String document) {
        return new ResponseEntity<>(customerService.getCustomerByDocument(document), OK);
    }

    @GetMapping("/type/{customerType}")
    public ResponseEntity<Page<CustomerDTO>> getCustomerByType(@PathVariable("customerType") CustomerType customerType,
            @PageableDefault(sort = "firstname", direction = Sort.Direction.ASC, size = DEFAULT_REQUEST_SIZE) Pageable pageable) {
        return new ResponseEntity<>(customerService.getCustomerByCustomerType(customerType, pageable), OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id,
            @RequestBody @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
