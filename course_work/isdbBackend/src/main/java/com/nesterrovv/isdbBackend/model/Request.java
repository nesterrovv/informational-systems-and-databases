package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@Data
@Table(name = "request")
public class Request {

    @Id
    @Column
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    Integer request_id;
    @Column
    Integer order_id;
    @Column
    String description;
    @Column
    Double weight;
    @Column
    Double length;
    @Column
    Double width;
    @Column
    Double height;

}
