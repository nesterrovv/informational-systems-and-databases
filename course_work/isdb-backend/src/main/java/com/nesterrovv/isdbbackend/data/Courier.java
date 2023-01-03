package com.nesterrovv.isdbbackend.data;

import lombok.Data;

@Data
public class Courier {

    private int courier_id;
    private String name;
    private String surname;
    private double rating;
    private int balance;
    private String login;
    private String password;

}
