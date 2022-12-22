package com.nesterrovv.isdbBackend.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Service {

    private static final String DB_URL = "jdbc:postgresql://localhost:5430/studs";
    private static final String USER = "s312621";
    private static final String PASS = "my_password_is_not_your_business";
    private final Connection connection;

    public Service() {
        Connection connection1;
        try {
            connection1 = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException sqlException) {
            connection1 = null;
            sqlException.printStackTrace();
            System.exit(1);
        }
        this.connection = connection1;
    }

    public Connection getConnection() {
        return connection;
    }

}
