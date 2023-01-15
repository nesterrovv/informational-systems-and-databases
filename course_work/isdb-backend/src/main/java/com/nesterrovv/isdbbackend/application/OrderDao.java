package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDao {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class OrderDTO {
        private int order_id;
        private int customer_id;
        private String departure_point;
        private String destination_point;
        private OrderStatus status;
        private String description;
        private List<Good> goods;
    }

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

    public OrderDTO getOrderByIdForView(int id) {
       Ordering order = getOrderById(id);
       String departurePoint = "";
       String sql1 = "SELECT * FROM location WHERE location_id = :id";
       SqlParameterSource parameterSource1 = new MapSqlParameterSource()
               .addValue("id", order.getDeparture_point_id());
       Location departure = template.queryForObject(sql1, parameterSource1, (rs, rowNum) -> {
            Location location = new Location();
            location.setLocation_id(rs.getInt("location_id"));
            location.setName((rs.getString("name")));
            return location;
       });
       if (departure.getName() != null) {
           departurePoint = departure.getName();
       }
        String destinationPoint = "";
        String sql2 = "SELECT * FROM location WHERE location_id = :id";
        SqlParameterSource parameterSource2 = new MapSqlParameterSource()
                .addValue("id", order.getDestination_point_id());
        Location destination = template.queryForObject(sql2, parameterSource2, (rs, rowNum) -> {
            Location location = new Location();
            location.setLocation_id(rs.getInt("location_id"));
            location.setName((rs.getString("name")));
            return location;
        });
        if (destination.getName() != null) {
            destinationPoint = destination.getName();
        }
        List<Good> goodsInOrder = new ArrayList<>();
        List<Integer> goodIDs = template.query(
                "SELECT * FROM order_good WHERE order_id = " + id,
                (rs, rowNum) -> rs.getInt("good_id"));
        for (int goodID : goodIDs) {
            String sql3 = "SELECT * FROM good WHERE good_id = :id";
            SqlParameterSource parameterSource3  = new MapSqlParameterSource()
                    .addValue("id", goodID);
            Good current_good = template.queryForObject(sql3, parameterSource3, (rs, rowNum) -> {
                Good good = new Good();
                good.setGood_id(rs.getInt("good_id"));
                good.setStatus(GoodStatus.valueOf(rs.getString("status")));
                good.setDimensions(Dimensions.valueOf(rs.getString("dimensions")));
                good.setRequest_id(rs.getInt("request_id"));
                return good;
            });
            goodsInOrder.add(current_good);
        }
        return new OrderDTO(order.getOrder_id(), order.getCustomer_id(), departurePoint, destinationPoint,
                order.getStatus(), order.getDescription(), goodsInOrder);
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
                        rs.getInt("id"),
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
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("departure_point_id"),
                        rs.getInt("destination_point_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("description")));
        return orders;
    }
}
