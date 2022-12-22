package com.nesterrovv.isdbBackend.services;

import com.nesterrovv.isdbBackend.model.Courier;
import com.nesterrovv.isdbBackend.repositories.CourierRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CourierService implements CourierRepository {

    private final Connection connection;

    public CourierService() {
        com.nesterrovv.isdbBackend.services.Service service = new com.nesterrovv.isdbBackend.services.Service();
        this.connection = service.getConnection();
    }

    @Transactional
    public boolean save(Courier courier) {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "INSERT INTO courier (courier_id, name, surname, rating, balance, username, password) " +
                    "VALUES + (" + courier.getCourier_id() + " " + courier.getName() + " " + courier.getSurname() +
                    " " + courier.getRating() + " " + courier.getBalance() + " " + courier.getUsername() + " " +
                    courier.getPassword() + ")";
            stmt.executeUpdate(request);
            return true;
        } catch (SQLException sqlException) {
            System.err.println("Database schema constraints error. Try later.");
            System.exit(1);
            return false;
        }
    }

    @Transactional
    public void delete(Courier courier) {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "DELETE FROM courier WHERE courier.courier_id = " + courier.getCourier_id();
            stmt.executeUpdate(request);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Transactional
    public boolean exists(Integer primaryKey) {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "SELECT FROM courier WHERE courier.courier_id = " + primaryKey;
            ResultSet rs = stmt.executeQuery(request);
            int counter = 0;
            while(rs.next()){
                counter++;
            }
            rs.close();
            if (counter == 1) {
                return true;
            } else if (counter == 0) {
                return false;
            } else {
                System.err.println("Database schema constraints error. Try later.");
                System.exit(1);
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Courier findOne(Integer primaryKey) {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "SELECT FROM courier WHERE courier.courier_id = " + primaryKey;
            ResultSet rs = stmt.executeQuery(request);
            int counter = 0;
            Courier courier = new Courier();
            while(rs.next()){
                counter++;
                courier.setCourier_id(rs.getInt("courier_id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
                courier.setRating(rs.getDouble("rating"));
                courier.setBalance(rs.getDouble("balance"));
                courier.setUsername(rs.getString("username"));
                courier.setPassword(rs.getString("password"));
            }
            rs.close();
            if (counter == 1) {
                return courier;
            } else if (counter == 0) {
                return null;
            } else {
                System.err.println("Database schema constraints error. Try later.");
                System.exit(1);
                return null;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<Courier> findAll() {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "SELECT * FROM courier";
            ResultSet rs = stmt.executeQuery(request);
            List<Courier> couriers = new ArrayList<>();
            while(rs.next()){
                Courier courier = new Courier();
                courier.setCourier_id(rs.getInt("courier_id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
                courier.setRating(rs.getDouble("rating"));
                courier.setBalance(rs.getDouble("balance"));
                courier.setUsername(rs.getString("username"));
                courier.setPassword(rs.getString("password"));
                couriers.add(courier);
            }
            rs.close();
            return couriers;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public Courier findByUsername(String username) {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "SELECT * FROM courier WHERE courier.username = " + username;
            ResultSet rs = stmt.executeQuery(request);
            int counter = 0;
            Courier courier = new Courier();
            while(rs.next()){
                counter++;
                courier.setCourier_id(rs.getInt("courier_id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
                courier.setRating(rs.getDouble("rating"));
                courier.setBalance(rs.getDouble("balance"));
                courier.setUsername(rs.getString("username"));
                courier.setPassword(rs.getString("password"));
            }
            rs.close();
            if (counter == 1) {
                return courier;
            } else if (counter == 0) {
                return null;
            } else {
                System.err.println("Database schema constraints error. Try later.");
                System.exit(1);
                return null;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public Courier getCourierByUsernameAndPassword(String username, String password) {
        try {
            Statement stmt = this.connection.createStatement();
            String request = "SELECT * FROM courier WHERE courier.username = " + username +
                    " AND courier.password = " + password;
            ResultSet rs = stmt.executeQuery(request);
            int counter = 0;
            Courier courier = new Courier();
            while(rs.next()){
                counter++;
                courier.setCourier_id(rs.getInt("courier_id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
                courier.setRating(rs.getDouble("rating"));
                courier.setBalance(rs.getDouble("balance"));
                courier.setUsername(rs.getString("username"));
                courier.setPassword(rs.getString("password"));
            }
            rs.close();
            if (counter == 1) {
                return courier;
            } else if (counter == 0) {
                return null;
            } else {
                System.err.println("Database schema constraints error. Try later.");
                System.exit(1);
                return null;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public UserDetails loadCourierByUsername(String username) {
        Courier courier = findByUsername(username);
        if (courier == null) {
            return null;
        }
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }
            @Override
            public String getPassword() {
                return courier.getPassword();
            }

            @Override
            public String getUsername() {
                return courier.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return userDetails;
    }

}
