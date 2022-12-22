package com.nesterrovv.isdbBackend.controllers;

import com.nesterrovv.isdbBackend.config.jwt.JWTProvider;
import com.nesterrovv.isdbBackend.dto.CourierDTO;
import com.nesterrovv.isdbBackend.dto.CustomerDTO;
import com.nesterrovv.isdbBackend.dto.GoodDTO;
import com.nesterrovv.isdbBackend.model.Good;
import com.nesterrovv.isdbBackend.model.GoodStatus;
import com.nesterrovv.isdbBackend.services.CustomerService;
import com.nesterrovv.isdbBackend.services.GoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private final CustomerService customerService;
    private final GoodService goodService;
    private final JWTProvider jwtProvider;
    private final AuthenticationManager authManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };

    public CustomerController(CustomerService customerService, GoodService goodService, JWTProvider jwtProvider) {
        this.customerService = customerService;
        this.goodService = goodService;
        this.jwtProvider = jwtProvider;
    }

    @CrossOrigin
    @PostMapping("/get-list-of-my-orders")
    private ResponseEntity<List<Good>> getListOfOrders(@Valid @RequestBody CustomerDTO customerDTO, GoodDTO goodDTO) {
        List<Good> goods = goodService.findAll();
        List<Good> necessaryGoods = new ArrayList<>();
        for (Good good : goods) {
            if (good.getCourier_id().equals(goodDTO.getCourier_id())) {
                necessaryGoods.add(good);
            }
        }
        return new ResponseEntity<>(necessaryGoods, HttpStatus.OK);
    }


}
