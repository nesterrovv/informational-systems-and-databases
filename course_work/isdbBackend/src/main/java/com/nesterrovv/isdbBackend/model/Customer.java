package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @Column
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    Integer customer_id;
    @Column
    String name;
    @Column
    String surname;
    @Column
    String username;
    @Column
    String password;

}
