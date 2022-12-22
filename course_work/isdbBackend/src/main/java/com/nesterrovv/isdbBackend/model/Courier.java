package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@Accessors(chain = true)
@Entity
@Data
@Table(name = "courier")
public class Courier {

    @Id
    @Column
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    Integer courier_id;
    @Column
    String name;
    @Column
    String surname;
    @Column
    double rating;
    @Column
    double balance;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

}
