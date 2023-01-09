package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Courier;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourierController {

    private final CourierDao dao;

    public CourierController(CourierDao dao) {
        this.dao = dao;
    }

    @PostMapping(value = "/create-courier")
    public Integer createCourier(@RequestBody Courier courier) {
        return dao.createCourier(courier);
    }

    @GetMapping(value = "/get-courier")
    public Courier getCustomerById(@RequestParam int id) {
        return dao.getCourierById(id);
    }

    @PutMapping(value = "/update-courier")
    public void updateCourier(@RequestBody Courier courier) {
        dao.updateCourier(courier);
    }

    @DeleteMapping(value = "/delete-courier")
    public void deleteCourier(@RequestParam int id) {
        dao.deleteCourier(id);
    }

}
