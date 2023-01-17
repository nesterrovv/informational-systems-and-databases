package com.nesterrovv.isdbbackend.application;

import com.nesterrovv.isdbbackend.data.Good;
import com.nesterrovv.isdbbackend.data.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
