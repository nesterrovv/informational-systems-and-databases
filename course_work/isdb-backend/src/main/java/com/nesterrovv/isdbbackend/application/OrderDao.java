package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class GoodDTO {
        private GoodStatus status;
        private double weight;
        private double length;
        private double width;
        private double height;
        private String description;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class FrontendOrderDTO {
        private int customer_id;
        private String departure_point;
        private String description_point;
        private OrderStatus status;
        private String description;
        private List<GoodDTO> goods;

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
                good.setDescription(rs.getString("description"));
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
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("departure_point_id"),
                        rs.getInt("destination_point_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("description")));
        return orders;
    }

    public List<OrderDTO> getAllOrdersForView() {
        List<Ordering> orders = template.query(
                "SELECT * FROM ordering",
                (rs, rowNum) -> new Ordering(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("departure_point_id"),
                        rs.getInt("destination_point_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("description")));
        List<OrderDTO> ordersForView = new ArrayList<>();
        for (Ordering order : orders) {
            ordersForView.add(getOrderByIdForView(order.getOrder_id()));
        }
        return ordersForView;
    }

    public List<Ordering> getAllOrdersByCustomer(int customerId) {
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

    public List<OrderDTO> getAllOrdersByCustomerForView(int customerId) {
        List<Ordering> orders = getAllOrdersByCustomer(customerId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Ordering order : orders) {
            orderDTOs.add(getOrderByIdForView(order.getOrder_id()));
        }
        return orderDTOs;
    }

    public List<Ordering> getAllOrdersByCourier(int courierId) {
        List<Ordering> orders = template.query(
                "SELECT * FROM ordering WHERE order_id = " +
                        "(SELECT order_id FROM courier_order WHERE courier_id = " + courierId,
                (rs, rowNum) -> new Ordering(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("departure_point_id"),
                        rs.getInt("destination_point_id"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("description")));
        return orders;
    }

    public List<OrderDTO> getAllOrdersByCourierForView(int courierId) {
        List<Ordering> orders = getAllOrdersByCourier(courierId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Ordering order : orders) {
            orderDTOs.add(getOrderByIdForView(order.getOrder_id()));
        }
        return orderDTOs;
    }

    public Integer createOrderViaDTO(FrontendOrderDTO frontendOrderDTO) {
        String sql1 = "SELECT * FROM location WHERE name = :name";
        SqlParameterSource parameterSource1 = new MapSqlParameterSource()
                .addValue("name", frontendOrderDTO.getDeparture_point());
        Location departure = template.queryForObject(sql1, parameterSource1, (rs, rowNum) -> {
            Location location1 = new Location();
            location1.setLocation_id(rs.getInt("location_id"));
            location1.setName(rs.getString("name"));
            return location1;
        });
        String sql2 = "SELECT * FROM location WHERE name = :name";
        SqlParameterSource parameterSource2 = new MapSqlParameterSource()
                .addValue("name", frontendOrderDTO.getDeparture_point());
        Location destination = template.queryForObject(sql2, parameterSource2, (rs, rowNum) -> {
            Location location1 = new Location();
            location1.setLocation_id(rs.getInt("location_id"));
            location1.setName(rs.getString("name"));
            return location1;
        });
        String sql3 = "INSERT INTO ordering (customer_id, departure_point_id, destination_point_id, status, " +
                "description) VALUES (:customer_id, :departure_point_id, :destination_point_id, :status, :description) " +
                "RETURNING order_id";
        SqlParameterSource parameterSource3 = new MapSqlParameterSource()
                .addValue("customer_id", frontendOrderDTO.getCustomer_id())
                .addValue("departure_point_id", departure.getLocation_id())
                .addValue("destination_point_id", destination.getLocation_id())
                .addValue("status", frontendOrderDTO.getStatus())
                .addValue("description", frontendOrderDTO.getDescription());
        Integer newOrderId = template.queryForObject(sql3, parameterSource3, Integer.class);
        for (GoodDTO goodDTO : frontendOrderDTO.getGoods()) {
            String sql4 = "INSERT INTO request (order_id, description, weight, length, width, height) VALUES " +
                    "(:order_id, :description, :weight, :length, :width, :height) RETURNING request_id";
            SqlParameterSource parameterSource4 = new MapSqlParameterSource()
                    .addValue("order_id", newOrderId)
                    .addValue("description", goodDTO.getDescription())
                    .addValue("weight", goodDTO.getWeight())
                    .addValue("length", goodDTO.getLength())
                    .addValue("width", goodDTO.getWidth())
                    .addValue("height", goodDTO.getHeight());
            Integer newRequestId = template.queryForObject(sql4, parameterSource4, Integer.class);
            // after this, good will be created automatically (via triggers in database)
            String sql5 = "SELECT * FROM good WHERE request_id = :request_id";
            SqlParameterSource parameterSource5 = new MapSqlParameterSource()
                    .addValue("request_id", newRequestId);
            Integer newGoodId = template.queryForObject(sql5, parameterSource5, Integer.class);
            String sql6 = "INSERT INTO order_good (good_id, order_id) VALUES (:good_id, :order_id) RETURNING order_id";
            SqlParameterSource parameterSource6 = new MapSqlParameterSource()
                    .addValue("good_id", newGoodId)
                    .addValue("order_id", newOrderId);
            template.queryForObject(sql6, parameterSource6, Integer.class);
        }
        return newOrderId;
    }

    public Integer updateOrderStatus(int courier_id, int order_id, OrderStatus newStatus) {
        if (newStatus.equals(OrderStatus.DELIVERING)) {
            String sql2 = "UPDATE courier_order SET courier_id = :courier_id WHERE order_id = :order_id";
            SqlParameterSource parameterSource2 = new MapSqlParameterSource()
                    .addValue("courier_id", courier_id)
                    .addValue("order_id", order_id);
            template.update(sql2, parameterSource2);
        } else if (newStatus.equals(OrderStatus.DELIVERED) || newStatus.equals(OrderStatus.DESTROYED)) {
            String sql3 = "DELETE FROM courier_order WHERE order_id = :order_id";
            SqlParameterSource parameterSource3 = new MapSqlParameterSource()
                    .addValue("order_id", order_id);
            template.update(sql3, parameterSource3);
        }
        String sql1 = "UPDATE ordering SET status = :status WHERE order_id = :order_id";
        SqlParameterSource parameterSource1 = new MapSqlParameterSource()
                .addValue("status", newStatus)
                .addValue("order_id", order_id);
        template.update(sql1, parameterSource1);
        return order_id;
    }

    public Integer updateGoodStatus(int good_id, GoodStatus newStatus) {
        String sql1 = "UPDATE good SET status = :good_status WHERE good_id = :good_id";
        SqlParameterSource parameterSource1 = new MapSqlParameterSource()
                .addValue("good_status", newStatus)
                .addValue("good_id", good_id);
        template.update(sql1, parameterSource1);
        return good_id;
    }

}
