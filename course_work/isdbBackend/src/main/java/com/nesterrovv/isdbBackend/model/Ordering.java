package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
public class Ordering {

    Integer order_id;
    Integer customer_id;
    Integer departure_point_id;
    Integer destination_point_id;
    OrderStatus status;
    String description;

}
