package com.uninter.baozistore.core.service.customer;

import com.uninter.baozistore.core.common.Messages;
import com.uninter.baozistore.core.model.Customer;
import com.uninter.baozistore.core.repository.customer.CustomerRepository;
import com.uninter.baozistore.infrastructure.controller.customer.CustomerOutput;
import com.uninter.baozistore.infrastructure.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerOutput createCustomer(String name) {
        Customer customer = Customer.builder()
                .name(name)
                .customerSince(LocalDate.now(ZoneId.of("America/Sao_Paulo")))
                .build();
        customer = customerRepository.save(customer);
        return new CustomerOutput(customer.getId(), customer.getName(), customer.getCustomerSince());
    }

    public CustomerOutput updateCustomer(Long id, String name) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.CUSTOMER_NOT_FOUND + " " + id));
        customer.setName(name);
        customer = customerRepository.save(customer);
        return new CustomerOutput(customer.getId(), customer.getName(), customer.getCustomerSince());
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.CUSTOMER_NOT_FOUND + " " + id));
        customerRepository.delete(customer);
    }

    public List<CustomerOutput> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerOutput(customer.getId(), customer.getName(), customer.getCustomerSince()))
                .toList();
    }

    public CustomerOutput getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> new CustomerOutput(customer.getId(), customer.getName(), customer.getCustomerSince()))
                .orElseThrow(() -> new NotFoundException(Messages.CUSTOMER_NOT_FOUND + " " + id));
    }

}
