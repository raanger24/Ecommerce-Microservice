package com.ecommerce.orders.service;

import com.ecommerce.orders.dao.CustomerRepository;
import com.ecommerce.orders.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }

    public Customer getOrCreateCustomer(Customer customer) {
        if(customer.getId() == null) {
            return customerRepository.save(customer);
        }
        return customerRepository.findById(customer.getId().toString()).get();
    }


}
