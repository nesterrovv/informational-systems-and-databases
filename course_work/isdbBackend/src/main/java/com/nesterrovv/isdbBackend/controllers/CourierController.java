package com.nesterrovv.isdbBackend.controllers;

import com.nesterrovv.isdbBackend.config.jwt.JWTProvider;
import com.nesterrovv.isdbBackend.dto.CourierDTO;
import com.nesterrovv.isdbBackend.dto.GoodDTO;
import com.nesterrovv.isdbBackend.model.Courier;
import com.nesterrovv.isdbBackend.model.Good;
import com.nesterrovv.isdbBackend.model.GoodStatus;
import com.nesterrovv.isdbBackend.services.CourierService;
import com.nesterrovv.isdbBackend.services.GoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(path = "/api/courier")
public class CourierController {
    private final CourierService courierService;
    private final GoodService goodService;
    private final JWTProvider jwtProvider;
    private final AuthenticationManager authManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };

    public CourierController(CourierService courierService, GoodService goodService, JWTProvider jwtProvider) {
        this.courierService = courierService;
        this.goodService = goodService;
        this.jwtProvider = jwtProvider;
    }

    private boolean checkCourierData(CourierDTO courierDTO) {
        return courierDTO.getUsername() == null || courierDTO.getPassword() == null || courierDTO.getUsername().trim().equals("")
                || courierDTO.getPassword().trim().equals("");
    }

    private String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] passwordDigest = md.digest();
            return new String(Base64.getEncoder().encode(passwordDigest));
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password", e);
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    private ResponseEntity<String> login(@Valid @RequestBody CourierDTO courierDTO) {
        if (checkCourierData(courierDTO)) {
            return new ResponseEntity<>("Unacceptable username and/or password.", HttpStatus.BAD_REQUEST);
        }
        String username = courierDTO.getUsername();
        String password = encodePassword(courierDTO.getPassword());
        Courier courier = courierService.getCourierByUsernameAndPassword(username, password);
        if (courier != null) {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtProvider.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return new ResponseEntity<>("Account not found or username and/or password are incorrect.",
                    HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/register")
    private ResponseEntity<String> register(@Valid @RequestBody CourierDTO courierDTO) {
        if (checkCourierData(courierDTO)) {
            return new ResponseEntity<>("Unacceptable username and/or password.", HttpStatus.BAD_REQUEST);
        }
        String username = courierDTO.getUsername();
        String password = encodePassword(courierDTO.getPassword());
        Courier courier = new Courier();
        courier.setUsername(username);
        courier.setPassword(password);
        courier.setName(courierDTO.getName());
        courier.setSurname(courierDTO.getSurname());
        if (courierService.save(courier)) {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtProvider.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return new ResponseEntity<>("Account is already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/get-list-free-orders")
    private ResponseEntity<List<Good>> getListOfOrders(@Valid @RequestBody CourierDTO courierDTO) {
        List<Good> goods = goodService.findAll();
        List<Good> necessaryGoods = new ArrayList<>();
        for (Good good : goods) {
            if (!good.getStatus().equals(GoodStatus.DELIVERED) || good.getStatus().equals(GoodStatus.DESTROYED)) {
                necessaryGoods.add(good);
            }
        }
        return new ResponseEntity<>(necessaryGoods, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/get-my-active-orders")
    private ResponseEntity<List<Good>> getListOfActiveOrders(@Valid @RequestBody CourierDTO courierDTO) {
        List<Good> goods = goodService.findAll();
        List<Good> necessaryGoods = new ArrayList<>();
        for (Good good : goods) {
            if (good.getStatus().equals(GoodStatus.DELIVERING) &&
                    good.getCourier_id().equals(courierDTO.getCourier_id())) {
                necessaryGoods.add(good);
            }
        }
        return new ResponseEntity<>(necessaryGoods, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/change-good-status")
    private ResponseEntity<String> changeGoodStatus(@Valid @RequestBody CourierDTO courierDTO, GoodDTO goodDTO,
                                                        GoodStatus newGoodStatus) {
        Good good = goodService.findOne(goodDTO.getId());
        if (good.getStatus().equals(GoodStatus.DELIVERING) &&
                good.getCourier_id().equals(courierDTO.getCourier_id())) {
            good.setStatus(newGoodStatus);
            goodService.save(good);
            return new ResponseEntity<>("Changes saved.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Permission for changing good status denied.", HttpStatus.FORBIDDEN);
    }

}
