package com.nesterrovv.isdbBackend.services;

import com.nesterrovv.isdbBackend.model.Request;
import com.nesterrovv.isdbBackend.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Transactional
    public Request save(Request request) {
        Request requestFromDB = this.requestRepository.findOne(request.getRequest_id());
        if (requestFromDB != null) {
            return null;
        }
        this.requestRepository.save(request);
        return request;
    }

    @Transactional
    public void delete(Request request) {
        this.requestRepository.delete(request);
    }

    @Transactional
    public boolean exists(Integer primaryKey) {
        return requestRepository.exists(primaryKey);
    }

    @Transactional
    public Request findOne(Integer primaryKey) {
        return requestRepository.findOne(primaryKey);
    }

    @Transactional
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

}
