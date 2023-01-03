package com.nesterrovv.isdbbackend.data;

import lombok.Data;

@Data
public class Customer {

    private int customer_id;
    private String name;
    private String surname;
    private String login;
    private String password;

}
