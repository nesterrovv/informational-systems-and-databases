package com.nesterrovv.isdbBackend.services;

import com.nesterrovv.isdbBackend.model.Courier;
import com.nesterrovv.isdbBackend.repositories.CourierRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Transactional
    public boolean save(Courier courier) {
        Courier courierFromDB = this.courierRepository.findOne(courier.getCourier_id());
        if (courierFromDB != null) {
            return null;
        }
        this.courierRepository.save(courier);
        return courier;
    }

    @Transactional
    public void delete(Courier courier) {
        this.courierRepository.delete(courier);
    }

    @Transactional
    public boolean exists(Integer primaryKey) {
        return courierRepository.exists(primaryKey);
    }

    @Transactional
    public Courier findOne(Integer primaryKey) {
        return courierRepository.findOne(primaryKey);
    }

    @Transactional
    public List<Courier> findAll() {
        return courierRepository.findAll();
    }

    public Courier findByUsername(String username) {
        return courierRepository.findByUsername(username);
    }
    public Courier getCourierByUsernameAndPassword(String username, String password) {
        return courierRepository.getCourierByUsernameAndPassword(username, password);
    }

    public UserDetails loadCourierByUsername(String username) {
        return courierRepository.loadCourierByUsername(username);
    }

}
