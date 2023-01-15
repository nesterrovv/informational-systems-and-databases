package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Customer;
import com.nesterrovv.isdbbackend.data.OrderStatus;
import com.nesterrovv.isdbbackend.data.Ordering;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDao {

    private final NamedParameterJdbcTemplate template;

    public OrderDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Integer createOrder(Ordering order) {
        String sql = "INSERT INTO ordering (customer_id, departure_point_id, destination_point_id, status, description) " +
                "VALUES (:customer_id, :departure_point_id, :destination_point_id, :status, :description)" +
                " RETURNING order_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("customer_id", order.getCustomer_id())
                .addValue("departure_point_id", order.getDeparture_point_id())
                .addValue("destination_point_id", order.getDestination_point_id())
                .addValue("status", order.getStatus())
                .addValue("description", order.getDescription());
        return template.queryForObject(sql, parameterSource, Integer.class);
    }

    public Ordering getOrderById(int id) {
        String sql = "SELECT * FROM ordering WHERE order_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Ordering order = new Ordering();
            order.setOrder_id(rs.getInt("order_id"));
            order.setCustomer_id(rs.getInt("customer_id"));
            order.setDeparture_point_id(rs.getInt("departure_point_id"));
            order.setDestination_point_id(rs.getInt("destination_point_id"));
            order.setStatus(OrderStatus.valueOf(rs.getString("status")));
            order.setDescription(rs.getString("description"));
            return order;
        });
    }

    public void updateOrder(Ordering order) {
        String sql = "UPDATE ordering SET customer_id = :customer_id, departure_point_id = :departure_point_id, " +
                "destination_point_id = :destination_point_id, status = :status, description = :description WHERE " +
                "order_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", order.getOrder_id())
                .addValue("customer_id", order.getCustomer_id())
                .addValue("departure_point_id", order.getDeparture_point_id())
                .addValue("destination_point_id", order.getDestination_point_id())
                .addValue("status", order.getStatus())
                .addValue("description", order.getDescription());
        template.update(sql, parameterSource);
    }

    public void deleteCourier(int id) {
        String sql = "DELETE FROM ordering WHERE order_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, parameterSource);
    }

    public List<Ordering> findAll() {
        List<Ordering> orders = template.query(
                "SELECT * FROM ordering",
                (rs, rowNum) -> new Ordering(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("departure_point_id"),
                        rs.getInt("destination_point_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("description")));
        return orders;
    }

    public List<Ordering> findAllByCustomer(int customerId) {
        List<Ordering> orders = template.query(
                "SELECT * FROM ordering WHERE customer_id = " + customerId,
                (rs, rowNum) -> new Ordering(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("departure_point_id"),
                        rs.getInt("destination_point_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("description")));
        return orders;
    }
}
