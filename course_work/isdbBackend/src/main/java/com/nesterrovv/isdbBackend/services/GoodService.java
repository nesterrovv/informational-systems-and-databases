package com.nesterrovv.isdbBackend.services;

import com.nesterrovv.isdbBackend.model.Good;
import com.nesterrovv.isdbBackend.repositories.GoodRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GoodService {

    private final GoodRepository goodRepository;

    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Transactional
    public Good save(Good good) {
        Good goodFromDB = this.goodRepository.findOne(good.getGood_id());
        if (goodFromDB != null) {
            return null;
        }
        this.goodRepository.save(good);
        return good;
    }

    @Transactional
    public void delete(Good good) {
        this.goodRepository.delete(good);
    }

    @Transactional
    public boolean exists(Integer primaryKey) {
        return goodRepository.exists(primaryKey);
    }

    @Transactional
    public Good findOne(Integer primaryKey) {
        return goodRepository.findOne(primaryKey);
    }

    @Transactional
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

}
