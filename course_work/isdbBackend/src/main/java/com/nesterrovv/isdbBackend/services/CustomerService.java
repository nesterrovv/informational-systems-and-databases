package com.nesterrovv.isdbBackend.services;

import com.nesterrovv.isdbBackend.model.Customer;
import com.nesterrovv.isdbBackend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer save(Customer customer) {
        Customer customerFromDB = this.customerRepository.findOne(customer.getCustomer_id());
        if (customerFromDB != null) {
            return null;
        }
        this.customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }

    @Transactional
    public boolean exists(Integer primaryKey) {
        return customerRepository.exists(primaryKey);
    }

    @Transactional
    public Customer findOne(Integer primaryKey) {
        return customerRepository.findOne(primaryKey);
    }

    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

}
