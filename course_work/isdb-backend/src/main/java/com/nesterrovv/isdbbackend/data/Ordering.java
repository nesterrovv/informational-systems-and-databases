package com.nesterrovv.isdbbackend.data;

import lombok.Data;

@Data
public class Ordering {

    private int order_id;
    private int customer_id;
    private int departure_point_id;
    private int destination_point_id;
    private OrderStatus status;
    private String description;

}
