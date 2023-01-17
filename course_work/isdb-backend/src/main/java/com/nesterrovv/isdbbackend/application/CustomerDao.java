package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Customer;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class CustomerDao {

    private final NamedParameterJdbcTemplate template;
    private boolean customerIsLoggedIn;

    public CustomerDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Integer createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, surname, login, password) " +
                "VALUES (:name, :surname, :login, :password) RETURNING customer_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", customer.getName())
                .addValue("surname", customer.getSurname())
                .addValue("login", customer.getLogin())
                .addValue("password", customer.getPassword());
        return template.queryForObject(sql, parameterSource, Integer.class);
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setCustomer_id(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            customer.setSurname(rs.getString("surname"));
            customer.setLogin(rs.getString("login"));
            customer.setPassword(rs.getString("password"));
            return customer;
        });
    }

    public void updateCourier(Customer customer) {
        String sql = "UPDATE customer SET name = :name, surname = :surname WHERE customer_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", customer.getCustomer_id())
                .addValue("name", customer.getName())
                .addValue("surname", customer.getSurname());
        template.update(sql, parameterSource);
    }

    public void deleteCourier(int id) {
        String sql = "DELETE FROM customer WHERE customer_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, parameterSource);
    }

    public String loginCustomer(@RequestBody CustomerDTO customerDTO) {
        String sql1 = "SELECT * FROM customer WHERE login = :login and password = :password";
        SqlParameterSource parameterSource1 = new MapSqlParameterSource()
                .addValue("login", customerDTO.getLogin())
                .addValue("password", customerDTO.getPassword());
        CustomerDTO load = template.queryForObject(sql1, parameterSource1, (rs, rowNum) -> {
            CustomerDTO customerDTO1 = new CustomerDTO();
            customerDTO1.setLogin(rs.getString("login"));
            customerDTO1.setPassword(rs.getString("password"));
            return customerDTO1;
        });
        if (customerDTO.getLogin().equals(load.getLogin()) &&
                customerDTO.getPassword().equals(load.getPassword())) {
            customerIsLoggedIn = true;
            return customerDTO.getLogin();
        }
        return null;
    }

    public void logoutCustomer() {
        customerIsLoggedIn = false;
    }

    public boolean checkIfLoggedIn() {
        return customerIsLoggedIn;
    }


}
