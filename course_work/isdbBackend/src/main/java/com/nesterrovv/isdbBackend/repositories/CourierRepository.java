package com.nesterrovv.isdbBackend.repositories;

import com.nesterrovv.isdbBackend.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Integer> {

    Courier save(Courier courier);
    void delete(Courier courier);
    boolean exists(Integer primaryKey);
    Courier findOne(Integer primaryKey);
    List<Courier> findAll();
    Courier findByUsername(String username);
    Courier getCourierByUsernameAndPassword(String username, String password);
    UserDetails loadCourierByUsername(String username);
}
