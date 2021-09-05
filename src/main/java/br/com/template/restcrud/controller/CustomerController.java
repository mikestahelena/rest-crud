package br.com.template.restcrud.controller;

import br.com.template.restcrud.domain.CustomerDTO;
import br.com.template.restcrud.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<CustomerDTO>> getCustomersByName(@RequestParam String name) {
        return new ResponseEntity<>(customerService.getCustomersByName(name), OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
