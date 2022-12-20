package com.nesterrovv.isdbBackend.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Entity
@Data
public class Ordering {

    @Id
    @Column
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    Integer order_id;
    @Column
    Integer customer_id;
    @Column
    Integer departure_point_id;
    @Column
    Integer destination_point_id;
    @Column
    OrderStatus status;
    @Column
    String description;

}
