package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Customer;
import com.nesterrovv.isdbbackend.data.Ordering;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
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

    @GetMapping(value = "/get-all-orders-by-customer")
    public List<Ordering> getAllOrdersByCustomer(@RequestParam int id) {
        return dao.getAllOrdersByCustomer(id);
    }

    @GetMapping(value = "/get-all-orders-for-view-by-customer")
    public List<OrderDao.OrderDTO> getAllOrdersForViewByCustomer(@RequestParam int id) {
        return dao.getAllOrdersByCustomerForView(id);
    }

    @GetMapping(value = "/get-order-for-view")
    public OrderDao.OrderDTO getOrderByIdForView(@RequestParam int id) {
        return dao.getOrderByIdForView(id);
    }

    @GetMapping(value = "/get-all-orders-for-view")
    public List<OrderDao.OrderDTO> getAlOrdersByIdForView() {
        return dao.getAllOrdersForView();
    }

}
