package com.nesterrovv.isdbBackend.repositories;

import com.nesterrovv.isdbBackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository {

    boolean save(Customer customer);
    void delete(Customer customer);
    boolean exists(Integer primaryKey);
    Customer findOne(Integer primaryKey);
    List<Customer> findAll();

}
