package com.nesterrovv.isdbbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ordering {

    private int order_id;
    private int customer_id;
    private int departure_point_id;
    private int destination_point_id;
    private OrderStatus status;
    private String description;

}
