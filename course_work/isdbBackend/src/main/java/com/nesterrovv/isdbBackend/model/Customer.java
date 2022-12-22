package com.nesterrovv.isdbBackend.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Accessors(chain = true)
@Data
public class Customer {

    Integer customer_id;
    String name;
    String surname;
    String username;
    String password;

}
