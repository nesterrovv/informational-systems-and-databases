package com.nesterrovv.isdbbackend.data;

import lombok.Data;

@Data
public class CourierOrder {

    private int courier_order_id;
    private int courier_id;
    private int order_id;

}
