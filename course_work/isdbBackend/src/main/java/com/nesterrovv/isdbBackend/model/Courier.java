package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@Data
public class Courier {

    Integer courier_id;
    String name;
    String surname;
    double rating;
    double balance;
    private String username;
    private String password;

}
