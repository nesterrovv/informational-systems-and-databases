package com.nesterrovv.isdbBackend.dao;

import com.nesterrovv.isdbBackend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CustomerDAO {

    private final NamedParameterJdbcTemplate template;

    public CustomerDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    public Long createCustomer(Customer customer) {
        //TODO: make logic
        String sql = "INSERT INTO customer (login, )";
        return -1L;
    }
}
