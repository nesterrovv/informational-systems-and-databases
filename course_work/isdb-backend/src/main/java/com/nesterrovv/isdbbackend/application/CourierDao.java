package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Courier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class CourierDao {

    private final NamedParameterJdbcTemplate template;
    private boolean courierIsLoggedIn;

    public CourierDao(NamedParameterJdbcTemplate template) {
            this.template = template;
    }

    public Integer createCourier(Courier courier) {
        String sql = "INSERT INTO courier (name, surname, rating, balance, login, password) " +
                "VALUES (:name, :surname, :rating, :balance, :login, :password) RETURNING courier_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", courier.getName())
                .addValue("surname", courier.getSurname())
                .addValue("rating", courier.getRating())
                .addValue("balance", courier.getBalance())
                .addValue("login", courier.getLogin())
                .addValue("password", courier.getPassword());
        return template.queryForObject(sql, parameterSource, Integer.class);
    }

    public Courier getCourierById(int id) {
        String sql = "SELECT * FROM courier WHERE courier_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Courier courier = new Courier();
            courier.setCourier_id(rs.getInt("courier_id"));
            courier.setName(rs.getString("name"));
            courier.setSurname(rs.getString("surname"));
            courier.setRating(rs.getDouble("rating"));
            courier.setBalance(rs.getInt("balance"));
            courier.setLogin(rs.getString("login"));
            courier.setPassword(rs.getString("password"));
            return courier;
        });
    }

    public void updateCourier(Courier courier) {
        String sql = "UPDATE courier SET name = :name, surname = :surname, rating = :rating, balance = " +
                ":balance WHERE courier_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", courier.getCourier_id())
                .addValue("name", courier.getName())
                .addValue("surname", courier.getSurname())
                .addValue("rating", courier.getRating())
                .addValue("balance", courier.getBalance());
        template.update(sql, parameterSource);
    }

    public void deleteCourier(int id) {
        String sql = "DELETE FROM courier WHERE courier_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, parameterSource);
    }

    public String loginCourier(@RequestBody CourierDTO courierDTO) {
        String sql1 = "SELECT * FROM courier WHERE login = :login and password = :password";
        SqlParameterSource parameterSource1 = new MapSqlParameterSource()
                .addValue("login", courierDTO.getLogin())
                .addValue("password", courierDTO.getPassword());
        CourierDTO load = template.queryForObject(sql1, parameterSource1, (rs, rowNum) -> {
            CourierDTO courierDTO1 = new CourierDTO();
            courierDTO1.setLogin(rs.getString("login"));
            courierDTO1.setPassword(rs.getString("password"));
            return courierDTO1;
        });
        if (courierDTO.getLogin().equals(load.getLogin()) &&
                courierDTO.getPassword().equals(load.getPassword())) {
            courierIsLoggedIn = true;
            return courierDTO.getLogin();
        }
        return null;
    }

    public void logoutCourier() {
        courierIsLoggedIn = false;
    }

    public boolean checkIfLoggedIn() {
        return courierIsLoggedIn;
    }
}
