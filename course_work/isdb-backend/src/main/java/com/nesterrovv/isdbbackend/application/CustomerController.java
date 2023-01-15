package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Customer;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
public class CustomerController {

    private final CustomerDao dao;

    public CustomerController(CustomerDao dao) {
        this.dao = dao;
    }

    @PostMapping(value = "/create-customer")
    public Integer createCourier(@RequestBody Customer customer) {
        return dao.createCustomer(customer);
    }

    @GetMapping(value = "/get-customer")
    public Customer getCustomerById(@RequestParam int id) {
        return dao.getCustomerById(id);
    }

    @PutMapping(value = "/update-customer")
    public void updateCourier(@RequestBody Customer customer) {
        dao.updateCourier(customer);
    }

    @DeleteMapping(value = "/delete-customer")
    public void deleteCourier(@RequestParam int id) {
        dao.deleteCourier(id);
    }

}
