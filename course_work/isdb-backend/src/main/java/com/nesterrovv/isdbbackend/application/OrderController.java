package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Customer;
import com.nesterrovv.isdbbackend.data.GoodStatus;
import com.nesterrovv.isdbbackend.data.OrderStatus;
import com.nesterrovv.isdbbackend.data.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
public class OrderController {

    private final OrderDao dao;
    @Autowired
    private CourierDao courierDao;
    @Autowired
    private CustomerDao customerDao;

    public OrderController(OrderDao dao) {
        this.dao = dao;
    }

    @PostMapping(value = "/create-order")
    public Integer createOrder(@RequestBody Ordering order) {
        return dao.createOrder(order);
    }

    @GetMapping(value = "/get-order")
    public Ordering getOrderById(@RequestParam int id) {
        return dao.getOrderById(id);
    }

    @PutMapping(value = "/update-order")
    public void updateOrder(@RequestBody Ordering order) {
        dao.updateOrder(order);
    }

    @DeleteMapping(value = "/delete-order")
    public void deleteOrder(@RequestParam int id) {
        dao.deleteCourier(id);
    }

    @GetMapping(value = "/get-all-orders")
    public List<Ordering> getAllOrders() {
        return dao.findAll();
    }

    @GetMapping(value = "/get-all-orders-by-customer")
    public List<Ordering> getAllOrdersByCustomer(@RequestParam int id) {
        if (customerDao.checkIfLoggedIn()) {
            return dao.getAllOrdersByCustomer(id);
        }
        return null;
    }

    @GetMapping(value = "/get-all-orders-for-view-by-customer")
    public List<OrderDao.OrderDTO> getAllOrdersForViewByCustomer(@RequestParam int id) {
        if (customerDao.checkIfLoggedIn()) {
            return dao.getAllOrdersByCustomerForView(id);
        }
        return null;
    }

    @GetMapping(value = "/get-order-for-view")
    public OrderDao.OrderDTO getOrderByIdForView(@RequestParam int id) {
        return dao.getOrderByIdForView(id);
    }

    @GetMapping(value = "/get-all-orders-for-view")
    public List<OrderDao.OrderDTO> getAlOrdersByIdForView() {
        return dao.getAllOrdersForView();
    }

    @GetMapping(value = "/get-all-orders-by-courier")
    public List<Ordering> getAllOrdersByCourier(@RequestParam int courierId) {
        if (courierDao.checkIfLoggedIn()) {
            return dao.getAllOrdersByCourier(courierId);
        }
        return null;
    }

    @GetMapping(value = "/get-all-orders-for-view-by-courier")
    public List<OrderDao.OrderDTO> getAllOrdersByCourierForView(@RequestParam int courierId) {
        if (courierDao.checkIfLoggedIn()) {
            return dao.getAllOrdersByCourierForView(courierId);
        }
        return null;
    }

    @PostMapping(value = "/create-order-via-dto")
    public Integer createOrderViaDTO(@RequestBody FrontendOrderDTO frontendOrderDTO) {
        if (customerDao.checkIfLoggedIn()) {
            return dao.createOrderViaDTO(frontendOrderDTO);
        }
        return null;
    }


    @PutMapping(value = "/update-order-status")
    public Integer updateOrderStatus(@RequestParam int courier_id, @RequestParam int order_id,
                                     @RequestParam OrderStatus newStatus) {
        if (courierDao.checkIfLoggedIn()) {
            return dao.updateOrderStatus(courier_id, order_id, newStatus);
        }
        return null;
    }

    @PutMapping(value = "/update-good-status")
    public Integer updateGoodStatus(@RequestParam  int good_id, @RequestParam GoodStatus newStatus) {
        return dao.updateGoodStatus(good_id, newStatus);
    }

}
