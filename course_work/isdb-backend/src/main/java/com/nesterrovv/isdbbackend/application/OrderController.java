package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Customer;
import com.nesterrovv.isdbbackend.data.Ordering;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderDao dao;

    public OrderController(OrderDao dao) {
        this.dao = dao;
    }

    @PostMapping(value = "/create-order")
    public Integer createCourier(@RequestBody Ordering order) {
        return dao.createOrder(order);
    }

    @GetMapping(value = "/get-order")
    public Ordering getCustomerById(@RequestParam int id) {
        return dao.getOrderById(id);
    }

    @PutMapping(value = "/update-order")
    public void updateCourier(@RequestBody Ordering order) {
        dao.updateOrder(order);
    }

    @DeleteMapping(value = "/delete-order")
    public void deleteCourier(@RequestParam int id) {
        dao.deleteCourier(id);
    }

    @GetMapping(value = "/get-all-orders")
    public List<Ordering> getAllOrders() {
        return dao.findAll();
    }

}
