package com.nesterrovv.isdbBackend.repositories;

import com.nesterrovv.isdbBackend.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Integer> {

    Good save(Good good);
    void delete(Good good);
    boolean exists(Integer primaryKey);
    Good findOne(Integer primaryKey);
    List<Good> findAll();

}
