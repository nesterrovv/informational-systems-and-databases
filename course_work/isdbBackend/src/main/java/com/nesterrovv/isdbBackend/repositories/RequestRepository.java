package com.nesterrovv.isdbBackend.repositories;

import com.nesterrovv.isdbBackend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request save(Request request);
    void delete(Request request);
    boolean exists(Integer primaryKey);
    Request findOne(Integer primaryKey);
    List<Request> findAll();

}
